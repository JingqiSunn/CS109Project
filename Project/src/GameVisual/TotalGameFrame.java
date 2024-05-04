package GameVisual;

import GameElement.ControllingCenter;
import GameVisual.Panels.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TotalGameFrame extends JFrame implements KeyListener, MouseListener {

    int totalWides;
    int totalHeight;

    Dimension screenSize;
    LoginPage loginPage;
    ModeChoosingPage modeChoosingPage;
    BoardSizeChoosingPage boardSizeChoosingPage;
    InGamePage inGamePage;
    BoardSizeDIYPage boardSizeDIYPage;
    Boolean whetherFullScreenNow;
    ControllingCenter controllingCenter;


    public TotalGameFrame() {
        controllingCenter = new ControllingCenter();
        this.setLayout(null);
        this.UpdateTheSizeOfTheScreen();
        this.SetFullScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.LoadLoginPage();
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setVisible(true);
    }

    void SetFullScreen() {
        GraphicsDevice currentDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (currentDevice.isFullScreenSupported()) {
            this.setUndecorated(true);
            currentDevice.setFullScreenWindow(this);
            whetherFullScreenNow = true;
        } else {
            System.out.println("Full screen is not supported in your device!");
        }
    }

    void outOfFullScreen() {
        GraphicsDevice currentDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (whetherFullScreenNow && currentDevice.isFullScreenSupported()) {
            currentDevice.setFullScreenWindow(null);
            whetherFullScreenNow = false;
            totalWides = 1100;
            totalHeight = 800;
            screenSize.setSize(totalWides, totalHeight);
            int xIndex = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - totalWides) / 2);
            int yIndex = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - totalHeight) / 2);
            this.setBounds(xIndex, yIndex, totalWides, totalHeight);
            setFocusable(true);
        }
    }

    void UpdateTheSizeOfTheScreen() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        totalWides = (int) screenSize.getWidth();
        totalHeight = (int) screenSize.getHeight();
    }

    void LoadLoginPage() {
        loginPage = new LoginPage(screenSize);
        loginPage.setVisible(true);
        this.add(loginPage);
        loginPage.getLoginOption().addMouseListener(this);
        loginPage.getTouristOption().addMouseListener(this);
        loginPage.getRegistrationOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadModeChoosingPage() {
        modeChoosingPage = new ModeChoosingPage(screenSize);
        modeChoosingPage.setVisible(true);
        this.add(modeChoosingPage);
        modeChoosingPage.getSinglePlayerOption().addMouseListener(this);
        modeChoosingPage.getMultiPlayersOption().addMouseListener(this);
        modeChoosingPage.getOtherModesOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadBoardSizeChoosingPage() {
        boardSizeChoosingPage = new BoardSizeChoosingPage(screenSize);
        boardSizeChoosingPage.setVisible(true);
        this.add(boardSizeChoosingPage);
        boardSizeChoosingPage.getThreeOption().addMouseListener(this);
        boardSizeChoosingPage.getFourOption().addMouseListener(this);
        boardSizeChoosingPage.getDIYOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadBoardSizeDIYPage() {
        boardSizeDIYPage = new BoardSizeDIYPage(screenSize);
        boardSizeDIYPage.setVisible(true);
        this.add(boardSizeDIYPage);
        for (int layerInRow = 0; layerInRow < 10; layerInRow++) {
            for (int layerInColumn = 0; layerInColumn < 10; layerInColumn++) {
                boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].addMouseListener(this);
            }
        }
        setFocusable(true);
    }
    void LoadInGamePage() {
        inGamePage = new InGamePage(screenSize,controllingCenter);
        inGamePage.setVisible(true);
        this.add(inGamePage);
        setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)   {
        int keyBeingActivated = e.getKeyCode();
        if (keyBeingActivated == KeyEvent.VK_ESCAPE) {
            this.outOfFullScreen();
            this.setVisible(true);
        }
        if (boardSizeDIYPage != null && keyBeingActivated == KeyEvent.VK_SPACE) {
            this.remove(boardSizeDIYPage);
            UpdateTheCoordinateSetInTheControllingCenter();
            boardSizeDIYPage = null;
            this.LoadInGamePage();
            repaint();
            setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component componentActivated = e.getComponent();
        if (loginPage != null && componentActivated.equals(loginPage.getTouristOption())) {
            remove(loginPage);
            loginPage = null;
            this.LoadModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }
        else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getSinglePlayerOption())) {
            remove(modeChoosingPage);
            modeChoosingPage = null;
            this.LoadBoardSizeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.getDIYOption())) {
            remove(boardSizeChoosingPage);
            boardSizeChoosingPage = null;
            this.LoadBoardSizeDIYPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }
        if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated)) {
            if (componentActivated instanceof UnitBlock) {
                if (!((UnitBlock) componentActivated).getWhetherChoosing()) {
                    componentActivated.setBackground(Color.LIGHT_GRAY);
                    Border borderOfTheBlock = BorderFactory.createLineBorder(Color.WHITE, 6, false);
                    ((UnitBlock) componentActivated).setBorder(borderOfTheBlock);
                    ((UnitBlock) componentActivated).setWhetherChoosing(true);
                    repaint();
                    componentActivated.setVisible(true);
                } else if (((UnitBlock) componentActivated).getWhetherChoosing()) {
                    componentActivated.setBackground(Color.WHITE);
                    Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK, 6, false);
                    ((UnitBlock) componentActivated).setBorder(borderOfTheBlock);
                    ((UnitBlock) componentActivated).setWhetherChoosing(false);
                    repaint();
                    componentActivated.setVisible(true);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component componentActivated = e.getComponent();
        if (loginPage != null && componentActivated.equals(loginPage.getLoginOption())) {
            loginPage.getLoginOption().setBackground(Color.BLACK);
            loginPage.getLoginOption().setVisible(true);
            loginPage.getLoginOption().repaint();
        } else if (loginPage != null && componentActivated.equals(loginPage.getTouristOption())) {
            loginPage.getTouristOption().setBackground(Color.BLACK);
            loginPage.getLoginOption().setVisible(true);
            loginPage.getTouristOption().repaint();
        } else if (loginPage != null && componentActivated.equals(loginPage.getRegistrationOption())) {
            loginPage.getRegistrationOption().setBackground(Color.BLACK);
            loginPage.getLoginOption().setVisible(true);
            loginPage.getRegistrationOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getSinglePlayerOption())) {
            modeChoosingPage.getSinglePlayerOption().setBackground(Color.BLACK);
            modeChoosingPage.getSinglePlayerOption().setVisible(true);
            modeChoosingPage.getSinglePlayerOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getMultiPlayersOption())) {
            modeChoosingPage.getMultiPlayersOption().setBackground(Color.BLACK);
            modeChoosingPage.getMultiPlayersOption().setVisible(true);
            modeChoosingPage.getMultiPlayersOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getOtherModesOption())) {
            modeChoosingPage.getOtherModesOption().setBackground(Color.BLACK);
            modeChoosingPage.getOtherModesOption().setVisible(true);
            modeChoosingPage.getOtherModesOption().repaint();
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.getThreeOption())) {
            boardSizeChoosingPage.getThreeOption().setBackground(Color.BLACK);
            boardSizeChoosingPage.getThreeOption().setVisible(true);
            boardSizeChoosingPage.getThreeOption().repaint();
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.getFourOption())) {
            boardSizeChoosingPage.getFourOption().setBackground(Color.BLACK);
            boardSizeChoosingPage.getFourOption().setVisible(true);
            boardSizeChoosingPage.getFourOption().repaint();
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.getDIYOption())) {
            boardSizeChoosingPage.getDIYOption().setBackground(Color.BLACK);
            boardSizeChoosingPage.getDIYOption().setVisible(true);
            boardSizeChoosingPage.getDIYOption().repaint();
        }
        if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && !((UnitBlock) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.BLACK);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.WHITE, 6, false);
            ((UnitBlock) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        }
        if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && ((UnitBlock) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.RED);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK, 6, false);
            ((UnitBlock) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component componentActivated = e.getComponent();
        if (loginPage != null && componentActivated.equals(loginPage.getLoginOption())) {
            loginPage.getLoginOption().setBackground(Color.LIGHT_GRAY);
            loginPage.getLoginOption().setVisible(true);
            loginPage.getLoginOption().repaint();
        } else if (loginPage != null && componentActivated.equals(loginPage.getTouristOption())) {
            loginPage.getTouristOption().setBackground(Color.LIGHT_GRAY);
            loginPage.getLoginOption().setVisible(true);
            loginPage.getTouristOption().repaint();
        } else if (loginPage != null && componentActivated.equals(loginPage.getRegistrationOption())) {
            loginPage.getRegistrationOption().setBackground(Color.LIGHT_GRAY);
            loginPage.getLoginOption().setVisible(true);
            loginPage.getRegistrationOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getSinglePlayerOption())) {
            modeChoosingPage.getSinglePlayerOption().setBackground(Color.LIGHT_GRAY);
            modeChoosingPage.getSinglePlayerOption().setVisible(true);
            modeChoosingPage.getSinglePlayerOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getMultiPlayersOption())) {
            modeChoosingPage.getMultiPlayersOption().setBackground(Color.LIGHT_GRAY);
            modeChoosingPage.getMultiPlayersOption().setVisible(true);
            modeChoosingPage.getMultiPlayersOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getOtherModesOption())) {
            modeChoosingPage.getOtherModesOption().setBackground(Color.LIGHT_GRAY);
            modeChoosingPage.getOtherModesOption().setVisible(true);
            modeChoosingPage.getOtherModesOption().repaint();
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.getThreeOption())) {
            boardSizeChoosingPage.getThreeOption().setBackground(Color.LIGHT_GRAY);
            boardSizeChoosingPage.getThreeOption().setVisible(true);
            boardSizeChoosingPage.getThreeOption().repaint();
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.getFourOption())) {
            boardSizeChoosingPage.getFourOption().setBackground(Color.LIGHT_GRAY);
            boardSizeChoosingPage.getFourOption().setVisible(true);
            boardSizeChoosingPage.getFourOption().repaint();
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.getDIYOption())) {
            boardSizeChoosingPage.getDIYOption().setBackground(Color.LIGHT_GRAY);
            boardSizeChoosingPage.getDIYOption().setVisible(true);
            boardSizeChoosingPage.getDIYOption().repaint();
        }
        if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && !((UnitBlock) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.WHITE);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK, 6, false);
            ((UnitBlock) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        }
        if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && ((UnitBlock) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.LIGHT_GRAY);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.WHITE, 6, false);
            ((UnitBlock) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        }
    }

    private boolean whetherTheComponentIsBelongingToTheBlocks(Component componentActivated) {
        boolean whetherBelong = false;
        for (int layerInRow = 0; layerInRow < 10; layerInRow++) {
            for (int layerInColumn = 0; layerInColumn < 10; layerInColumn++) {
                if (componentActivated.equals(boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn])) {
                    whetherBelong = true;
                    return whetherBelong;
                }
            }
        }
        return whetherBelong;
    }

    private void UpdateTheCoordinateSetInTheControllingCenter() {
        ArrayList<Integer> currentInformationAboutCoordinate = new ArrayList<>();
        for (int layerInRow = 0; layerInRow < 10; layerInRow++) {
            for (int layerInColumn = 0; layerInColumn < 10; layerInColumn++) {
                if (boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].getWhetherChoosing()) {
                    currentInformationAboutCoordinate.add(boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].getxCoordinate());
                    currentInformationAboutCoordinate.add(boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].getyCoordinate());
                }
            }
        }
        currentInformationAboutCoordinate = getMinimumXCoordinates(currentInformationAboutCoordinate);
        currentInformationAboutCoordinate = getMinimumYCoordinates(currentInformationAboutCoordinate);
        controllingCenter.setInformationOfAllTheCoordinateOfTheBoardUnit(currentInformationAboutCoordinate);
    }

    private ArrayList<Integer> getMinimumXCoordinates(ArrayList<Integer> originalCoordinateSet) {
        ArrayList<Integer> lastCoordinateInformation = new ArrayList<>();
        lastCoordinateInformation.addAll(originalCoordinateSet);
        ArrayList<Integer> currentCoordinateInformation = new ArrayList<>();
        currentCoordinateInformation.addAll(lastCoordinateInformation);
        boolean whetherContinue = true;
        while (whetherContinue) {
            for (int indexInPoints = 0; indexInPoints < lastCoordinateInformation.size() / 2; indexInPoints++) {
                currentCoordinateInformation.set(indexInPoints * 2, currentCoordinateInformation.get(indexInPoints * 2) - 1);
                if (currentCoordinateInformation.get(indexInPoints * 2) < 0) {
                    whetherContinue = false;
                    break;
                }
            }
            if (whetherContinue){
                lastCoordinateInformation = new ArrayList<>();
                lastCoordinateInformation.addAll(currentCoordinateInformation);
            }
        }
        return lastCoordinateInformation;
    }
    private ArrayList<Integer> getMinimumYCoordinates(ArrayList<Integer> originalCoordinateSet) {
        ArrayList<Integer> lastCoordinateInformation = new ArrayList<>();
        lastCoordinateInformation.addAll(originalCoordinateSet);
        ArrayList<Integer> currentCoordinateInformation = new ArrayList<>();
        currentCoordinateInformation.addAll(lastCoordinateInformation);
        boolean whetherContinue = true;
        while (whetherContinue) {
            for (int indexInPoints = 0; indexInPoints < lastCoordinateInformation.size() / 2; indexInPoints++) {
                currentCoordinateInformation.set(indexInPoints * 2+1, currentCoordinateInformation.get(indexInPoints * 2+1) - 1);
                if (currentCoordinateInformation.get(indexInPoints * 2+1) < 0) {
                    whetherContinue = false;
                    break;
                }
            }
            if (whetherContinue){
                lastCoordinateInformation = new ArrayList<>();
                lastCoordinateInformation.addAll(currentCoordinateInformation);
            }
        }
        return lastCoordinateInformation;
    }
}