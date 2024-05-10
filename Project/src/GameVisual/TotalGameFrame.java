package GameVisual;

import GameElement.BoardUnit;
import GameElement.ControllingCenter;
import GameSave.DocumentReaderAndWriter;
import GameVisual.Panels.*;
import MultiUserSupply.UserManger;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class TotalGameFrame extends JFrame implements KeyListener, MouseListener {

    int totalWides;
    int totalHeight;

    Dimension screenSize;
    LoginPage loginPage;
    ModeChoosingPage modeChoosingPage;
    BoardSizeChoosingPage boardSizeChoosingPage;
    TouristDiePage touristDiePage;
    InGamePage inGamePage;
    BoardSizeDIYPage boardSizeDIYPage;
    UserLoginPage userLoginPage;
    UserRegistrationPage userRegistrationPage;
    Boolean whetherFullScreenNow;
    ControllingCenter controllingCenter;
    DocumentReaderAndWriter documentReaderAndWriter;
    ArrayList<BoardUnit> currentBoardInformation;
    boolean timerIsRunning;
    public TotalGameFrame() {
        controllingCenter = new ControllingCenter();
        this.timerIsRunning = false;
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
    void LoadInGamePageForTourist() {
        inGamePage = new InGamePage(screenSize,controllingCenter,true);
        inGamePage.addKeyListener(this);
        inGamePage.setVisible(true);
        this.add(inGamePage);
        setFocusable(true);
    }
    void LoadTouristDiePage() {
        touristDiePage = new TouristDiePage(screenSize,controllingCenter,0);
        touristDiePage.setVisible(true);
        this.add(touristDiePage);
        touristDiePage.getBackToMenuOption().addMouseListener(this);
        touristDiePage.getRestartOption().addMouseListener(this);
        setFocusable(true);
    }
    void LoadUserLoginPage(){
        userLoginPage = new UserLoginPage(screenSize);
        userLoginPage.setVisible(true);
        this.add(userLoginPage);
        userLoginPage.GetClickHereToRegister().addMouseListener(this);
        userLoginPage.GetLoginConfirmPanel().addMouseListener(this);
        setFocusable(true);
    }
    void LoadUserRegistrationPage(){
        userRegistrationPage = new UserRegistrationPage(screenSize);
        userRegistrationPage.setVisible(true);
        this.add(userRegistrationPage);
        userRegistrationPage.GetClickHereToLoginPanel().addMouseListener(this);
        userRegistrationPage.GetRegistrationConfirmPanel().addMouseListener(this);
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
        }else if (boardSizeDIYPage != null && keyBeingActivated == KeyEvent.VK_SPACE) {
            this.remove(boardSizeDIYPage);
            UpdateTheCoordinateSetInTheControllingCenter();
            boardSizeDIYPage = null;
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTourist();
            repaint();
            setVisible(true);
        }else if (inGamePage != null && keyBeingActivated == KeyEvent.VK_UP){
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePage.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGame();
        } else if (inGamePage != null && keyBeingActivated == KeyEvent.VK_DOWN&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePage.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGame();
        } else if (inGamePage != null && keyBeingActivated == KeyEvent.VK_LEFT&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePage.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGame();
        } else if (inGamePage != null && keyBeingActivated == KeyEvent.VK_RIGHT&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.RightAction();
            inGamePage.UpdateBlockUnitsInGame();
            controllingCenter.UpdateGameValidity();
            this.repaint();
            this.JudgeWhetherEndOfGame();
        } else if (inGamePage != null && keyBeingActivated == KeyEvent.VK_R&&!timerIsRunning) {
            inGamePage.RestartTheGame();
        } else if (inGamePage != null && keyBeingActivated == KeyEvent.VK_S) {
            documentReaderAndWriter = new DocumentReaderAndWriter(controllingCenter);
            documentReaderAndWriter.save();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component componentActivated = e.getComponent();
        if (loginPage != null && componentActivated.equals(loginPage.getLoginOption())) {
            remove(loginPage);
            loginPage = null;
            this.LoadUserLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if(loginPage != null && componentActivated.equals(loginPage.getRegistrationOption())) {
            remove(loginPage);
            loginPage = null;
            this.LoadUserRegistrationPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if(loginPage != null && componentActivated.equals(loginPage.getTouristOption())) {
            remove(loginPage);
            loginPage = null;
            this.LoadModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getSinglePlayerOption())) {
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
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.FourOption)) {
            remove(boardSizeChoosingPage);
            boardSizeChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTourist();
            repaint();
            setVisible(true);
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.ThreeOption)) {
            remove(boardSizeChoosingPage);
            boardSizeChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForThree();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTourist();
            repaint();
            setVisible(true);
        } else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated)) {
            if (componentActivated instanceof UnitBlockInDIY) {
                if (!((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
                    componentActivated.setBackground(Color.LIGHT_GRAY);
                    Border borderOfTheBlock = BorderFactory.createLineBorder(Color.WHITE, 6, false);
                    ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
                    ((UnitBlockInDIY) componentActivated).setWhetherChoosing(true);
                    repaint();
                    componentActivated.setVisible(true);
                } else if (((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
                    componentActivated.setBackground(Color.WHITE);
                    Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK, 6, false);
                    ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
                    ((UnitBlockInDIY) componentActivated).setWhetherChoosing(false);
                    repaint();
                    componentActivated.setVisible(true);
                }
            }
        }else if (touristDiePage != null && componentActivated.equals(touristDiePage.getBackToMenuOption())) {
            controllingCenter = new ControllingCenter();
            this.remove(touristDiePage);
            touristDiePage = null;
            this.LoadBoardSizeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }else if (touristDiePage != null && componentActivated.equals(touristDiePage.getRestartOption())) {
            this.remove(touristDiePage);
            touristDiePage = null;
            controllingCenter.CleanThePlayingBoardForRestart();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTourist();
            repaint();
            setVisible(true);
        }else if(userLoginPage != null && componentActivated.equals(userLoginPage.GetClickHereToRegister())) {
            remove(userLoginPage);
            userLoginPage = null;
            this.LoadUserRegistrationPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }else if(userRegistrationPage != null && componentActivated.equals(userRegistrationPage.GetClickHereToLoginPanel())) {
            remove(userRegistrationPage);
            userRegistrationPage = null;
            this.LoadUserLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if(userRegistrationPage != null && componentActivated.equals(userRegistrationPage.GetRegistrationConfirmPanel())) {

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
        }else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && !((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.BLACK);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.WHITE, 6, false);
            ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        }else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && ((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.RED);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK, 6, false);
            ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        }else if (touristDiePage != null && componentActivated.equals(touristDiePage.getBackToMenuOption())) {
            touristDiePage.getBackToMenuOption().setBackground(Color.BLACK);
            touristDiePage.getBackToMenuOption().setVisible(true);
            touristDiePage.getBackToMenuOption().repaint();
        }else if (touristDiePage != null && componentActivated.equals(touristDiePage.getRestartOption())) {
            touristDiePage.getRestartOption().setBackground(Color.BLACK);
            touristDiePage.getRestartOption().setVisible(true);
            touristDiePage.getRestartOption().repaint();
        } else if (userLoginPage != null && componentActivated.equals(userLoginPage.GetLoginConfirmPanel())) {
            userLoginPage.GetLoginConfirmPanel().setBackground(new Color(0xDEAC80));
            userLoginPage.GetLoginConfirmPanel().setVisible(true);
            userLoginPage.GetLoginConfirmPanel().repaint();
        } else if (userRegistrationPage != null && componentActivated.equals(userRegistrationPage.GetRegistrationConfirmPanel())) {
            userRegistrationPage.GetRegistrationConfirmPanel().setBackground(new Color(0xE8EFCF));
            userRegistrationPage.GetRegistrationConfirmPanel().setVisible(true);
            userRegistrationPage.GetRegistrationConfirmPanel().repaint();
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
        }else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && !((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.WHITE);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK, 6, false);
            ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        }else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && ((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.LIGHT_GRAY);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.WHITE, 6, false);
            ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        }else if (touristDiePage != null && componentActivated.equals(touristDiePage.getBackToMenuOption())) {
            touristDiePage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            touristDiePage.getBackToMenuOption().setVisible(true);
            touristDiePage.getBackToMenuOption().repaint();
        }else if (touristDiePage != null && componentActivated.equals(touristDiePage.getRestartOption())) {
            touristDiePage.getRestartOption().setBackground(Color.LIGHT_GRAY);
            touristDiePage.getRestartOption().setVisible(true);
            touristDiePage.getRestartOption().repaint();
        } else if (userLoginPage != null && componentActivated.equals(userLoginPage.GetLoginConfirmPanel())) {
            userLoginPage.GetLoginConfirmPanel().setBackground(new Color(0xFFE6E6));
            userLoginPage.GetLoginConfirmPanel().setVisible(true);
            userLoginPage.GetLoginConfirmPanel().repaint();
        } else if (userRegistrationPage != null && componentActivated.equals(userRegistrationPage.GetRegistrationConfirmPanel())) {
            userRegistrationPage.GetRegistrationConfirmPanel().setBackground(new Color(0xF2EFE5));
            userRegistrationPage.GetRegistrationConfirmPanel().setVisible(true);
            userRegistrationPage.GetRegistrationConfirmPanel().repaint();
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
        controllingCenter.SetUpTheControllingCenterForDIY();
    }

    private void UpdateTheCoordinateSetInTheControllingCenterForFour(){
        ArrayList<Integer> currentInformationAboutCoordinate = new ArrayList<>();
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(3);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(3);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(3);
        currentInformationAboutCoordinate.add(3);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(3);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(3);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(3);
        currentInformationAboutCoordinate.add(3);
        controllingCenter.setInformationOfAllTheCoordinateOfTheBoardUnit(currentInformationAboutCoordinate);
        controllingCenter.SetUpTheControllingCenterForDIY();
    }
    private void UpdateTheCoordinateSetInTheControllingCenterForThree(){
        ArrayList<Integer> currentInformationAboutCoordinate = new ArrayList<>();
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(0);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(1);
        currentInformationAboutCoordinate.add(2);
        currentInformationAboutCoordinate.add(2);
        controllingCenter.setInformationOfAllTheCoordinateOfTheBoardUnit(currentInformationAboutCoordinate);
        controllingCenter.SetUpTheControllingCenterForDIY();
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
    public void JudgeWhetherEndOfGame() {
        controllingCenter.UpdateGameValidity();
        if (!controllingCenter.getGameValidity()) {
            timerIsRunning = true;
            Timer timer = new Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timerIsRunning = false;

                    if (inGamePage.GetWhetherTourist()) {
                        remove(inGamePage);
                        inGamePage = null;
                        LoadTouristDiePage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                        System.out.println("hi");
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    private void DealWithRegistrationIssue(){
        UserManger userManger= new UserManger();
        if (!userManger.ExamineValidityOfUserName(userRegistrationPage.GetUserName())){

        }
    }
}
