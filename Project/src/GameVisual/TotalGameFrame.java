package GameVisual;

import GameElement.BoardUnit;
import GameElement.ControllingCenter;
import GameSave.DocumentReaderAndWriter;
import GameVisual.Panels.*;
import MultiUserSupply.User;
import MultiUserSupply.UserManger;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class TotalGameFrame extends JFrame implements KeyListener, MouseListener {
    User user;
    int totalWides;
    int totalHeight;
    Dimension screenSize;
    LoginPage loginPage;
    ModeChoosingPage modeChoosingPage;
    BoardSizeChoosingPage boardSizeChoosingPage;
    TouristDiePage touristDiePage;
    InGamePageWithoutTimeLimit inGamePageWithoutTimeLimit;
    BoardSizeDIYPage boardSizeDIYPage;
    UserLoginPage userLoginPage;
    UserRegistrationPage userRegistrationPage;
    SuccessfullyRegisteredPage successfullyRegisteredPage;
    TimeLimitChoosingPage timeLimitChoosingPage;
    Boolean whetherFullScreenNow;
    ControllingCenter controllingCenter;
    DocumentReaderAndWriter documentReaderAndWriter;
    ArrayList<BoardUnit> currentBoardInformation;
    InGamePageWithTimeLimit inGamePageWithTimeLimit;
    TouristWinningPage touristWinningPage;
    UserGameTypeChoosingPage userGameTypeChoosingPage;
    UserSingleModeChoosingPage userSingleModeChoosingPage;
    UserCompetitionChoosingPage userCompetitionChoosingPage;
    UserWinningPage userWinningPage;
    UserCompetitionWithoutLimitDiePage userCompetitionWithoutLimitDiePage;
    UserCompetitionWithLimitDiePage userCompetitionWithLimitDiePage;
    boolean timerIsRunning;
    boolean winningPageIsOnShow;
    boolean skin;
    public TotalGameFrame() {
        skin = false;
        winningPageIsOnShow = false;
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
        modeChoosingPage.getNoTimeLimitationOption().addMouseListener(this);
        modeChoosingPage.getLimitedTimeOption().addMouseListener(this);
        modeChoosingPage.getRegisterOption().addMouseListener(this);
        setFocusable(true);
    }
    void LoadUserGameTypeChoosingPage() {
        userGameTypeChoosingPage = new UserGameTypeChoosingPage(screenSize,user);
        userGameTypeChoosingPage.setVisible(true);
        this.add(userGameTypeChoosingPage);
        userGameTypeChoosingPage.getSinglePlayerOption().addMouseListener(this);
        userGameTypeChoosingPage.getMultiPlayerOption().addMouseListener(this);
        userGameTypeChoosingPage.getRecordOption().addMouseListener(this);
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

    void LoadBoardSizeDIYPageWithoutTimeLimit() {
        boardSizeDIYPage = new BoardSizeDIYPage(screenSize,false);
        boardSizeDIYPage.setVisible(true);
        this.add(boardSizeDIYPage);
        for (int layerInRow = 0; layerInRow < 10; layerInRow++) {
            for (int layerInColumn = 0; layerInColumn < 10; layerInColumn++) {
                boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].addMouseListener(this);
            }
        }
        boardSizeDIYPage.GetContinueButton().addMouseListener(this);
        boardSizeDIYPage.getSkinSwitcher().addMouseListener(this);
        setFocusable(true);
    }
    void LoadBoardSizeDIYPageWithTimeLimit() {
        boardSizeDIYPage = new BoardSizeDIYPage(screenSize,true);
        boardSizeDIYPage.setVisible(true);
        this.add(boardSizeDIYPage);
        for (int layerInRow = 0; layerInRow < 10; layerInRow++) {
            for (int layerInColumn = 0; layerInColumn < 10; layerInColumn++) {
                boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].addMouseListener(this);
            }
        }
        boardSizeDIYPage.GetContinueButton().addMouseListener(this);
        boardSizeDIYPage.getSkinSwitcher().addMouseListener(this);
        setFocusable(true);
    }
    void LoadInGamePageForTouristWithoutTimeLimitation() {
        inGamePageWithoutTimeLimit = new InGamePageWithoutTimeLimit(screenSize,controllingCenter,true);
        inGamePageWithoutTimeLimit.addKeyListener(this);
        inGamePageWithoutTimeLimit.setVisible(true);
        inGamePageWithoutTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithoutTimeLimit);
        setFocusable(true);
    }
    void LoadInGamePageForUserWithoutTimeLimitation() {
        user.addOneGameTotalGameNumberForCompetitionWithoutLimit();
        inGamePageWithoutTimeLimit = new InGamePageWithoutTimeLimit(screenSize,controllingCenter,false,user,true,false);
        inGamePageWithoutTimeLimit.addKeyListener(this);
        inGamePageWithoutTimeLimit.setVisible(true);
        inGamePageWithoutTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithoutTimeLimit);
        setFocusable(true);
    }
    void LoadInGamePageForTouristWithTimeLimitation(int timeLimit){
        inGamePageWithTimeLimit = new InGamePageWithTimeLimit(screenSize,controllingCenter,true,timeLimit,this);
        inGamePageWithTimeLimit.addKeyListener(this);
        inGamePageWithTimeLimit.setVisible(true);
        inGamePageWithTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithTimeLimit);
        setFocusable(true);
    }
    void LoadInGamePageForUserWithTimeLimitation(int timeLimit){
        user.UpdateUserInformationForCompetition();
        user.UpdateTheAverageScoreForStartOfGameCompetitionWithTimeLimit();
        user.addOneGameTotalGameNumberForCompetitionWithLimit();
        inGamePageWithTimeLimit = new InGamePageWithTimeLimit(screenSize,controllingCenter,false,timeLimit,this,user,true);
        inGamePageWithTimeLimit.addKeyListener(this);
        inGamePageWithTimeLimit.setVisible(true);
        inGamePageWithTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithTimeLimit);
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
    void LoadUserCompetitionWithoutLimitDiePage() {
        user.UpdateBestFiveScoreForCompetitionWithoutTimeLimit(controllingCenter.getCurrentGameScore());
        if (controllingCenter.getCurrentGameScore()>=7000){
            user.IncreaseSevenThousandTimeInCompetitionWithoutTimeLimit();
        }
        if (controllingCenter.getCurrentGameScore()>=14000){
            user.IncreaseFourteenThousandTimeInCompetitionWithoutTimeLimit();
        }
        userCompetitionWithoutLimitDiePage = new UserCompetitionWithoutLimitDiePage(screenSize,controllingCenter,user);
        userCompetitionWithoutLimitDiePage.setVisible(true);
        this.add(userCompetitionWithoutLimitDiePage);
        userCompetitionWithoutLimitDiePage.getBackToMenuOption().addMouseListener(this);
        userCompetitionWithoutLimitDiePage.getRestartOption().addMouseListener(this);
        setFocusable(true);
    }
    void LoadUserCompetitionWithLimitDiePage() {
        user.UpdateBestFiveScoreForCompetitionWithTimeLimit(controllingCenter.getCurrentGameScore());
        user.UpdateTheAverageScoreForEndOfGameCompetitionWithTimeLimit(controllingCenter.getCurrentGameScore());
        userCompetitionWithLimitDiePage = new UserCompetitionWithLimitDiePage(screenSize,controllingCenter,user);
        userCompetitionWithLimitDiePage.setVisible(true);
        this.add(userCompetitionWithLimitDiePage);
        userCompetitionWithLimitDiePage.getBackToMenuOption().addMouseListener(this);
        userCompetitionWithLimitDiePage.getRestartOption().addMouseListener(this);
        setFocusable(true);
    }
    void LoadTouristWinningPage() {
        touristWinningPage = new TouristWinningPage(screenSize,controllingCenter);
        touristWinningPage.setVisible(true);
        this.add(touristWinningPage);
        touristWinningPage.getBackToMenuOption().addMouseListener(this);
        touristWinningPage.getContinueOption().addMouseListener(this);
        setFocusable(true);
    }
    void LoadUserWinningPage(){
        user.IncreaseWinningTimeInCompetitionWithoutTimeLimit();
        userWinningPage = new UserWinningPage(screenSize,controllingCenter,user);
        userWinningPage.setVisible(true);
        this.add(userWinningPage);
        userWinningPage.getBackToMenuOption().addMouseListener(this);
        userWinningPage.getContinueOption().addMouseListener(this);
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
    void LoadSuccessfulUserRegistrationPage(){
        this.add(successfullyRegisteredPage);
        setFocusable(true);
        this.addMouseListener(this);
        this.setFocusable(true);
        successfullyRegisteredPage.GetBackToMenu().addMouseListener(this);
        successfullyRegisteredPage.GetToLogin().addMouseListener(this);
        this.setVisible(true);
        repaint();
    }
    void LoadTimeLimitChoosingPage(){
        timeLimitChoosingPage = new TimeLimitChoosingPage(screenSize);
        timeLimitChoosingPage.setVisible(true);
        timeLimitChoosingPage.getThreeMinutesOption().addMouseListener(this);
        timeLimitChoosingPage.getSixMinutesOption().addMouseListener(this);
        timeLimitChoosingPage.getDIYOption().addMouseListener(this);
        this.add(timeLimitChoosingPage);
        setFocusable(true);
    }
    void LoadUserSingleModeChoosingPage(){
        userSingleModeChoosingPage = new UserSingleModeChoosingPage(screenSize,user);
        userSingleModeChoosingPage.setVisible(true);
        this.add(userSingleModeChoosingPage);
        userSingleModeChoosingPage.getCompetitionOption().addMouseListener(this);
        userSingleModeChoosingPage.getPracticeOption().addMouseListener(this);
        setFocusable(true);
    }
    void LoadUserCompetitionChoosingPage(){
        userCompetitionChoosingPage = new UserCompetitionChoosingPage(screenSize,user);
        userCompetitionChoosingPage.setVisible(true);
        this.add(userCompetitionChoosingPage);
        userCompetitionChoosingPage.getWithoutTimeLimitationOption().addMouseListener(this);
        userCompetitionChoosingPage.getWithTimeLimitationOption().addMouseListener(this);
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
        }else if (userRegistrationPage != null && e.isControlDown()&&e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userRegistrationPage);
            userRegistrationPage = null;
            this.LoadLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }else if (userLoginPage != null && e.isControlDown()&&e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userLoginPage);
            userLoginPage = null;
            this.LoadLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }else if (inGamePageWithoutTimeLimit != null &&!winningPageIsOnShow&& keyBeingActivated == KeyEvent.VK_UP&&!timerIsRunning){
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null &&!winningPageIsOnShow&& keyBeingActivated == KeyEvent.VK_DOWN&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null &&!winningPageIsOnShow&& keyBeingActivated == KeyEvent.VK_LEFT&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null&&!winningPageIsOnShow && keyBeingActivated == KeyEvent.VK_RIGHT&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.RightAction();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            controllingCenter.UpdateGameValidity();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && keyBeingActivated == KeyEvent.VK_R&&!timerIsRunning) {
            inGamePageWithoutTimeLimit.RestartTheGame();
        }else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_UP&&!timerIsRunning){
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_DOWN&&!timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_LEFT&&!timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_RIGHT&&!timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.RightAction();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            controllingCenter.UpdateGameValidity();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_R&&!timerIsRunning) {
            inGamePageWithTimeLimit.RestartTheGame();
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
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getNoTimeLimitationOption())) {
            remove(modeChoosingPage);
            modeChoosingPage = null;
            this.LoadBoardSizeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getLimitedTimeOption())) {
            remove(modeChoosingPage);
            modeChoosingPage = null;
            this.LoadTimeLimitChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getRegisterOption())) {
            remove(modeChoosingPage);
            modeChoosingPage = null;
            this.LoadUserRegistrationPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.getDIYOption())) {
            remove(boardSizeChoosingPage);
            boardSizeChoosingPage = null;
            this.LoadBoardSizeDIYPageWithoutTimeLimit();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.FourOption)) {
            remove(boardSizeChoosingPage);
            boardSizeChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTouristWithoutTimeLimitation();
            repaint();
            setVisible(true);
        } else if (boardSizeChoosingPage != null && componentActivated.equals(boardSizeChoosingPage.ThreeOption)) {
            remove(boardSizeChoosingPage);
            boardSizeChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForThree();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTouristWithoutTimeLimitation();
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
            skin = false;
            if (inGamePageWithTimeLimit != null){
                inGamePageWithTimeLimit = null;
                controllingCenter = new ControllingCenter();
                this.remove(touristDiePage);
                touristDiePage = null;
                this.LoadTimeLimitChoosingPage();
                this.addMouseListener(this);
                this.setFocusable(true);
                repaint();
                this.setVisible(true);
            }else {
                controllingCenter = new ControllingCenter();
                this.remove(touristDiePage);
                touristDiePage = null;
                this.LoadBoardSizeChoosingPage();
                this.addMouseListener(this);
                this.setFocusable(true);
                repaint();
                this.setVisible(true);
            }
        }else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getBackToMenuOption())) {
            skin = false;
            if (inGamePageWithTimeLimit != null){
                inGamePageWithTimeLimit = null;
                controllingCenter = new ControllingCenter();
                this.remove(userCompetitionWithoutLimitDiePage);
                userCompetitionWithoutLimitDiePage = null;
                this.LoadUserCompetitionChoosingPage();
                this.addMouseListener(this);
                this.setFocusable(true);
                repaint();
                this.setVisible(true);
            }else {
                controllingCenter = new ControllingCenter();
                this.remove(userCompetitionWithoutLimitDiePage);
                userCompetitionWithoutLimitDiePage = null;
                this.LoadUserCompetitionChoosingPage();
                this.addMouseListener(this);
                this.setFocusable(true);
                repaint();
                this.setVisible(true);
            }}else if (touristDiePage != null && componentActivated.equals(touristDiePage.getRestartOption())) {
            if ( inGamePageWithTimeLimit == null){
                this.remove(touristDiePage);
                touristDiePage = null;
                controllingCenter.setWhetherReachedTheTargetScore(false);
                controllingCenter.setWhetherAlreadyShownWinningPage(false);
                controllingCenter.CleanThePlayingBoardForRestart();
                controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
                this.LoadInGamePageForTouristWithoutTimeLimitation();
                repaint();
                setVisible(true);
            } else {
                int originalTimeLimit = inGamePageWithTimeLimit.getOriginalTimeLimit();
                this.remove(touristDiePage);
                touristDiePage = null;
                inGamePageWithTimeLimit = null;
                controllingCenter.CleanThePlayingBoardForRestart();
                controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
                controllingCenter.UpdateGameValidity();
                this.LoadInGamePageForTouristWithTimeLimitation(originalTimeLimit);
                repaint();
                setVisible(true);
            }
        }else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getRestartOption())) {
            user.UpdateUserInformationForCompetition();
                this.remove(userCompetitionWithoutLimitDiePage);
                userCompetitionWithoutLimitDiePage = null;
                controllingCenter.setWhetherReachedTheTargetScore(false);
                controllingCenter.setWhetherAlreadyShownWinningPage(false);
                controllingCenter.CleanThePlayingBoardForRestart();
                controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
                this.LoadInGamePageForUserWithoutTimeLimitation();
                repaint();
                setVisible(true);
        }else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getRestartOption())) {
                user.UpdateUserInformationForCompetition();
                int originalTimeLimit = inGamePageWithTimeLimit.getOriginalTimeLimit();
                this.remove(userCompetitionWithoutLimitDiePage);
                userCompetitionWithLimitDiePage = null;
                inGamePageWithTimeLimit = null;
                controllingCenter.CleanThePlayingBoardForRestart();
                controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
                controllingCenter.UpdateGameValidity();
                this.LoadInGamePageForUserWithTimeLimitation(originalTimeLimit);
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
            this.DealWithRegistrationIssue();
        } else if (successfullyRegisteredPage != null && componentActivated.equals(successfullyRegisteredPage.GetBackToMenu())) {
            this.remove(successfullyRegisteredPage);
            successfullyRegisteredPage = null;
            this.LoadLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (successfullyRegisteredPage != null && componentActivated.equals(successfullyRegisteredPage.GetToLogin())) {
            remove(successfullyRegisteredPage);
            successfullyRegisteredPage = null;
            this.LoadUserLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if(userLoginPage != null && componentActivated.equals(userLoginPage.GetLoginConfirmPanel())) {
            this.DealWithLoginIssue();
        } else if(boardSizeDIYPage != null &&!boardSizeDIYPage.getWhetherTimeLimited()&& componentActivated.equals(boardSizeDIYPage.GetContinueButton())){
            this.DealWithTheDIYSetting();
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getDIYOption())) {
            remove(timeLimitChoosingPage);
            timeLimitChoosingPage = null;
            this.LoadBoardSizeDIYPageWithTimeLimit();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }else if(boardSizeDIYPage != null &&boardSizeDIYPage.getWhetherTimeLimited()&& componentActivated.equals(boardSizeDIYPage.GetContinueButton())){
            this.DealWithTheDIYSetting();
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getThreeMinutesOption())) {
            remove(timeLimitChoosingPage);
            timeLimitChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTouristWithTimeLimitation(180);
            repaint();
            setVisible(true);
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getSixMinutesOption())) {
            remove(timeLimitChoosingPage);
            timeLimitChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTouristWithTimeLimitation(360);
            repaint();
            setVisible(true);
        }else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithTimeLimitationOption())) {
            remove(userCompetitionChoosingPage);
            userCompetitionChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.setInCompetition(true);
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForUserWithTimeLimitation(180);
            repaint();
            setVisible(true);
        }else if ((inGamePageWithTimeLimit !=null &&!inGamePageWithTimeLimit.GetWhetherDirectionButtonOut())&& componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.LoadButtonController();
            inGamePageWithTimeLimit.GetUpButton().addMouseListener(this);
            inGamePageWithTimeLimit.GetDownButton().addMouseListener(this);
            inGamePageWithTimeLimit.GetLeftButton().addMouseListener(this);
            inGamePageWithTimeLimit.GetRightButton().addMouseListener(this);
            repaint();
            setVisible(true);
        }else if (inGamePageWithoutTimeLimit !=null &&!inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.LoadButtonController();
            inGamePageWithoutTimeLimit.GetUpButton().addMouseListener(this);
            inGamePageWithoutTimeLimit.GetDownButton().addMouseListener(this);
            inGamePageWithoutTimeLimit.GetLeftButton().addMouseListener(this);
            inGamePageWithoutTimeLimit.GetRightButton().addMouseListener(this);
            repaint();
            setVisible(true);
        }else if ((inGamePageWithTimeLimit !=null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut())&& componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.CleanButtonController();
            repaint();
            setVisible(true);
        }else if (inGamePageWithoutTimeLimit !=null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.CleanButtonController();
            repaint();
            setVisible(true);
        }else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getBackToMenuOption())) {
            skin = false;
            inGamePageWithoutTimeLimit = null;
            controllingCenter = new ControllingCenter();
            this.remove(touristWinningPage);
            touristWinningPage = null;
            this.LoadBoardSizeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
            winningPageIsOnShow = false;
        }else if (userWinningPage != null && componentActivated.equals(userWinningPage.getBackToMenuOption())) {
            skin = false;
            inGamePageWithoutTimeLimit = null;
            controllingCenter = new ControllingCenter();
            this.remove(userWinningPage);
            userWinningPage = null;
            this.LoadUserCompetitionChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
            winningPageIsOnShow = false;
        }else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getContinueOption())) {
            this.remove(touristWinningPage);
            touristWinningPage = null;
            this.add(inGamePageWithoutTimeLimit);
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
            winningPageIsOnShow = false;
        }else if (userWinningPage != null && componentActivated.equals(userWinningPage.getContinueOption())) {
            this.remove(userWinningPage);
            userWinningPage = null;
            this.add(inGamePageWithoutTimeLimit);
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
            winningPageIsOnShow = false;
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetUpButton())&&!timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetDownButton())&&!timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetLeftButton())&&!timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetRightButton())&&!timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.RightAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        }else if (inGamePageWithoutTimeLimit != null&&!winningPageIsOnShow &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetUpButton())&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        }else if (inGamePageWithoutTimeLimit != null&&!winningPageIsOnShow &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetDownButton())&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        }else if (inGamePageWithoutTimeLimit != null&&!winningPageIsOnShow &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetLeftButton())&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        }else if (inGamePageWithoutTimeLimit != null&&!winningPageIsOnShow &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetRightButton())&&!timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.RightAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        }else if ((boardSizeDIYPage !=null &&componentActivated.equals(boardSizeDIYPage.getSkinSwitcher()))&&!skin) {
            skin=true;
        }else if ((boardSizeDIYPage !=null &&componentActivated.equals(boardSizeDIYPage.getSkinSwitcher()))&&skin) {
            skin = false;
        } else if (userGameTypeChoosingPage !=null && componentActivated.equals(userGameTypeChoosingPage.getSinglePlayerOption())){
            remove(userGameTypeChoosingPage);
            userGameTypeChoosingPage = null;
            this.LoadUserSingleModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }else if (userSingleModeChoosingPage !=null && componentActivated.equals(userSingleModeChoosingPage.getCompetitionOption())){
            remove(userSingleModeChoosingPage);
            userSingleModeChoosingPage = null;
            this.LoadUserCompetitionChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithoutTimeLimitationOption())) {
            remove(userCompetitionChoosingPage);
            userCompetitionChoosingPage = null;
            user.UpdateUserInformationForCompetition();
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            controllingCenter.setInCompetition(true);
            this.LoadInGamePageForUserWithoutTimeLimitation();
            repaint();
            setVisible(true);
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
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getNoTimeLimitationOption())) {
            modeChoosingPage.getNoTimeLimitationOption().setBackground(Color.BLACK);
            modeChoosingPage.getNoTimeLimitationOption().setVisible(true);
            modeChoosingPage.getNoTimeLimitationOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getLimitedTimeOption())) {
            modeChoosingPage.getLimitedTimeOption().setBackground(Color.BLACK);
            modeChoosingPage.getLimitedTimeOption().setVisible(true);
            modeChoosingPage.getLimitedTimeOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getRegisterOption())) {
            modeChoosingPage.getRegisterOption().setBackground(Color.BLACK);
            modeChoosingPage.getRegisterOption().setVisible(true);
            modeChoosingPage.getRegisterOption().repaint();
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
        }  else if (userLoginPage != null && componentActivated.equals(userLoginPage.GetLoginConfirmPanel())) {
            userLoginPage.GetLoginConfirmPanel().setBackground(new Color(0xDEAC80));
            userLoginPage.GetLoginConfirmPanel().setVisible(true);
            userLoginPage.GetLoginConfirmPanel().repaint();
        } else if (userRegistrationPage!=null&& userRegistrationPage.GetRegistrationConfirmPanel() != null && componentActivated.equals(userRegistrationPage.GetRegistrationConfirmPanel())) {
            userRegistrationPage.GetRegistrationConfirmPanel().setBackground(new Color(0xE8EFCF));
            userRegistrationPage.GetRegistrationConfirmPanel().setVisible(true);
            userRegistrationPage.GetRegistrationConfirmPanel().repaint();
        }else if (successfullyRegisteredPage != null && componentActivated.equals(successfullyRegisteredPage.GetBackToMenu())) {
            successfullyRegisteredPage.GetBackToMenu().setBackground(new Color(0xFDE49E));
            successfullyRegisteredPage.GetBackToMenu().setVisible(true);
            successfullyRegisteredPage.GetBackToMenu().repaint();
        } else if (successfullyRegisteredPage != null && componentActivated.equals(successfullyRegisteredPage.GetToLogin())) {
            successfullyRegisteredPage.GetToLogin().setBackground(new Color(0xFDE49E));
            successfullyRegisteredPage.GetToLogin().setVisible(true);
            successfullyRegisteredPage.GetToLogin().repaint();
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getThreeMinutesOption())) {
            timeLimitChoosingPage.getThreeMinutesOption().setBackground(Color.BLACK);
            timeLimitChoosingPage.getThreeMinutesOption().setVisible(true);
            timeLimitChoosingPage.getThreeMinutesOption().repaint();
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getSixMinutesOption())) {
            timeLimitChoosingPage.getSixMinutesOption().setBackground(Color.BLACK);
            timeLimitChoosingPage.getSixMinutesOption().setVisible(true);
            timeLimitChoosingPage.getSixMinutesOption().repaint();
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getDIYOption())) {
            timeLimitChoosingPage.getDIYOption().setBackground(Color.BLACK);
            timeLimitChoosingPage.getDIYOption().setVisible(true);
            timeLimitChoosingPage.getDIYOption().repaint();
        }else if(boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.GetContinueButton())){
            boardSizeDIYPage.GetContinueButton().setBackground(Color.BLACK);
            boardSizeDIYPage.GetContinueLabel().setForeground(Color.WHITE);
        } else if ((inGamePageWithTimeLimit !=null &&!inGamePageWithTimeLimit.GetWhetherDirectionButtonOut())&& componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.getButtonControllerSwitch().setBackground(Color.BLACK);
        }else if (inGamePageWithTimeLimit !=null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.getButtonControllerSwitch().setBackground(Color.RED);
        }else if ((inGamePageWithoutTimeLimit !=null &&!inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut())&& componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.getButtonControllerSwitch().setBackground(Color.BLACK);
        }else if (inGamePageWithoutTimeLimit !=null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.getButtonControllerSwitch().setBackground(Color.RED);
        }else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getBackToMenuOption())) {
            touristWinningPage.getBackToMenuOption().setBackground(Color.BLACK);
            touristWinningPage.getBackToMenuOption().setVisible(true);
            touristWinningPage.getBackToMenuOption().repaint();
        }else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getContinueOption())) {
            touristWinningPage.getContinueOption().setBackground(Color.BLACK);
            touristWinningPage.getContinueOption().setVisible(true);
            touristWinningPage.getContinueOption().repaint();
        }else if (userWinningPage != null && componentActivated.equals(userWinningPage.getBackToMenuOption())) {
            userWinningPage.getBackToMenuOption().setBackground(Color.BLACK);
            userWinningPage.getBackToMenuOption().setVisible(true);
            userWinningPage.getBackToMenuOption().repaint();
        }else if (userWinningPage != null && componentActivated.equals(userWinningPage.getContinueOption())) {
            userWinningPage.getContinueOption().setBackground(Color.BLACK);
            userWinningPage.getContinueOption().setVisible(true);
            userWinningPage.getContinueOption().repaint();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetUpButton())) {
            inGamePageWithTimeLimit.GetUpButton().setBackground(new Color(0xFF7657));
            inGamePageWithTimeLimit.GetUpButton().setVisible(true);
            inGamePageWithTimeLimit.GetUpButton().repaint();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetDownButton())) {
            inGamePageWithTimeLimit.GetDownButton().setBackground(new Color(0xFF7657));
            inGamePageWithTimeLimit.GetDownButton().setVisible(true);
            inGamePageWithTimeLimit.GetDownButton().repaint();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetLeftButton())) {
            inGamePageWithTimeLimit.GetLeftButton().setBackground(new Color(0xFF7657));
            inGamePageWithTimeLimit.GetLeftButton().setVisible(true);
            inGamePageWithTimeLimit.GetLeftButton().repaint();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetRightButton())) {
            inGamePageWithTimeLimit.GetRightButton().setBackground(new Color(0xFF7657));
            inGamePageWithTimeLimit.GetRightButton().setVisible(true);
            inGamePageWithTimeLimit.GetRightButton().repaint();
        }else if (inGamePageWithoutTimeLimit != null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetUpButton())) {
            inGamePageWithoutTimeLimit.GetUpButton().setBackground(new Color(0xFF7657));
            inGamePageWithoutTimeLimit.GetUpButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetUpButton().repaint();
        }else if (inGamePageWithoutTimeLimit != null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetDownButton())) {
            inGamePageWithoutTimeLimit.GetDownButton().setBackground(new Color(0xFF7657));
            inGamePageWithoutTimeLimit.GetDownButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetDownButton().repaint();
        }else if (inGamePageWithoutTimeLimit != null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetLeftButton())) {
            inGamePageWithoutTimeLimit.GetLeftButton().setBackground(new Color(0xFF7657));
            inGamePageWithoutTimeLimit.GetLeftButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetLeftButton().repaint();
        }else if (inGamePageWithoutTimeLimit != null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetRightButton())) {
            inGamePageWithoutTimeLimit.GetRightButton().setBackground(new Color(0xFF7657));
            inGamePageWithoutTimeLimit.GetRightButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetRightButton().repaint();
        }else if ((boardSizeDIYPage !=null &&componentActivated.equals(boardSizeDIYPage.getSkinSwitcher()))&&!skin) {
            boardSizeDIYPage.getSkinSwitcher().setBackground(Color.BLACK);
        }else if ((boardSizeDIYPage !=null &&componentActivated.equals(boardSizeDIYPage.getSkinSwitcher()))&&skin) {
            boardSizeDIYPage.getSkinSwitcher().setBackground(Color.RED);
        }else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getSinglePlayerOption())) {
            userGameTypeChoosingPage.getSinglePlayerOption().setBackground(Color.BLACK);
            userGameTypeChoosingPage.getSinglePlayerOption().setVisible(true);
            userGameTypeChoosingPage.getSinglePlayerOption().repaint();
        }else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getMultiPlayerOption())) {
            userGameTypeChoosingPage.getMultiPlayerOption().setBackground(Color.BLACK);
            userGameTypeChoosingPage.getMultiPlayerOption().setVisible(true);
            userGameTypeChoosingPage.getMultiPlayerOption().repaint();
        }else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getRecordOption())) {
            userGameTypeChoosingPage.getRecordOption().setBackground(Color.BLACK);
            userGameTypeChoosingPage.getRecordOption().setVisible(true);
            userGameTypeChoosingPage.getRecordOption().repaint();
        }else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getCompetitionOption())) {
            userSingleModeChoosingPage.getCompetitionOption().setBackground(Color.BLACK);
            userSingleModeChoosingPage.getCompetitionOption().setVisible(true);
            userSingleModeChoosingPage.getCompetitionOption().repaint();
        }else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getPracticeOption())) {
            userSingleModeChoosingPage.getPracticeOption().setBackground(Color.BLACK);
            userSingleModeChoosingPage.getPracticeOption().setVisible(true);
            userSingleModeChoosingPage.getPracticeOption().repaint();
        }else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithoutTimeLimitationOption())) {
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().setBackground(Color.BLACK);
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().setVisible(true);
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().repaint();
        }else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithTimeLimitationOption())) {
            userCompetitionChoosingPage.getWithTimeLimitationOption().setBackground(Color.BLACK);
            userCompetitionChoosingPage.getWithTimeLimitationOption().setVisible(true);
            userCompetitionChoosingPage.getWithTimeLimitationOption().repaint();
        }else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getBackToMenuOption())) {
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().setBackground(Color.BLACK);
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().setVisible(true);
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().repaint();
        }else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getRestartOption())) {
            userCompetitionWithoutLimitDiePage.getRestartOption().setBackground(Color.BLACK);
            userCompetitionWithoutLimitDiePage.getRestartOption().setVisible(true);
            userCompetitionWithoutLimitDiePage.getRestartOption().repaint();
        } else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getBackToMenuOption())) {
            userCompetitionWithLimitDiePage.getBackToMenuOption().setBackground(Color.BLACK);
            userCompetitionWithLimitDiePage.getBackToMenuOption().setVisible(true);
            userCompetitionWithLimitDiePage.getBackToMenuOption().repaint();
        }else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getRestartOption())) {
            userCompetitionWithLimitDiePage.getRestartOption().setBackground(Color.BLACK);
            userCompetitionWithLimitDiePage.getRestartOption().setVisible(true);
            userCompetitionWithLimitDiePage.getRestartOption().repaint();
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
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getNoTimeLimitationOption())) {
            modeChoosingPage.getNoTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            modeChoosingPage.getNoTimeLimitationOption().setVisible(true);
            modeChoosingPage.getNoTimeLimitationOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getLimitedTimeOption())) {
            modeChoosingPage.getLimitedTimeOption().setBackground(Color.LIGHT_GRAY);
            modeChoosingPage.getLimitedTimeOption().setVisible(true);
            modeChoosingPage.getLimitedTimeOption().repaint();
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getRegisterOption())) {
            modeChoosingPage.getRegisterOption().setBackground(Color.LIGHT_GRAY);
            modeChoosingPage.getRegisterOption().setVisible(true);
            modeChoosingPage.getRegisterOption().repaint();
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
        } else if (successfullyRegisteredPage != null && componentActivated.equals(successfullyRegisteredPage.GetBackToMenu())) {
            successfullyRegisteredPage.GetBackToMenu().setBackground(new Color(0x7C7575));
            successfullyRegisteredPage.GetBackToMenu().setVisible(true);
            successfullyRegisteredPage.GetBackToMenu().repaint();
        } else if (successfullyRegisteredPage != null && componentActivated.equals(successfullyRegisteredPage.GetToLogin())) {
            successfullyRegisteredPage.GetToLogin().setBackground(new Color(0x7C7575));
            successfullyRegisteredPage.GetToLogin().setVisible(true);
            successfullyRegisteredPage.GetToLogin().repaint();
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getThreeMinutesOption())) {
            timeLimitChoosingPage.getThreeMinutesOption().setBackground(Color.LIGHT_GRAY);
            timeLimitChoosingPage.getThreeMinutesOption().setVisible(true);
            timeLimitChoosingPage.getThreeMinutesOption().repaint();
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getSixMinutesOption())) {
            timeLimitChoosingPage.getSixMinutesOption().setBackground(Color.LIGHT_GRAY);
            timeLimitChoosingPage.getSixMinutesOption().setVisible(true);
            timeLimitChoosingPage.getSixMinutesOption().repaint();
        }else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getDIYOption())) {
            timeLimitChoosingPage.getDIYOption().setBackground(Color.LIGHT_GRAY);
            timeLimitChoosingPage.getDIYOption().setVisible(true);
            timeLimitChoosingPage.getDIYOption().repaint();
        }else if(boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.GetContinueButton())){
            boardSizeDIYPage.GetContinueButton().setBackground(Color.LIGHT_GRAY);
            boardSizeDIYPage.GetContinueLabel().setForeground(Color.BLACK);
        }else if (inGamePageWithTimeLimit !=null &&(!inGamePageWithTimeLimit.GetWhetherDirectionButtonOut())&& componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.getButtonControllerSwitch().setBackground(null);
        }else if (inGamePageWithTimeLimit !=null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&&componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.getButtonControllerSwitch().setBackground(null);
        }else if (inGamePageWithoutTimeLimit !=null &&(!inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut())&& componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.getButtonControllerSwitch().setBackground(null);
        }else if (inGamePageWithoutTimeLimit !=null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&&componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.getButtonControllerSwitch().setBackground(null);
        }else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getBackToMenuOption())) {
            touristWinningPage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            touristWinningPage.getBackToMenuOption().setVisible(true);
            touristWinningPage.getBackToMenuOption().repaint();
        }else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getContinueOption())) {
            touristWinningPage.getContinueOption().setBackground(Color.LIGHT_GRAY);
            touristWinningPage.getContinueOption().setVisible(true);
            touristWinningPage.getContinueOption().repaint();
        }else if (userWinningPage != null && componentActivated.equals(userWinningPage.getBackToMenuOption())) {
            userWinningPage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userWinningPage.getBackToMenuOption().setVisible(true);
            userWinningPage.getBackToMenuOption().repaint();
        }else if (userWinningPage != null && componentActivated.equals(userWinningPage.getContinueOption())) {
            userWinningPage.getContinueOption().setBackground(Color.LIGHT_GRAY);
            userWinningPage.getContinueOption().setVisible(true);
            userWinningPage.getContinueOption().repaint();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetUpButton())) {
            inGamePageWithTimeLimit.GetUpButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithTimeLimit.GetUpButton().setVisible(true);
            inGamePageWithTimeLimit.GetUpButton().repaint();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetDownButton())) {
            inGamePageWithTimeLimit.GetDownButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithTimeLimit.GetDownButton().setVisible(true);
            inGamePageWithTimeLimit.GetDownButton().repaint();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetLeftButton())) {
            inGamePageWithTimeLimit.GetLeftButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithTimeLimit.GetLeftButton().setVisible(true);
            inGamePageWithTimeLimit.GetLeftButton().repaint();
        }else if (inGamePageWithTimeLimit != null &&inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithTimeLimit.GetRightButton())) {
            inGamePageWithTimeLimit.GetRightButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithTimeLimit.GetRightButton().setVisible(true);
            inGamePageWithTimeLimit.GetRightButton().repaint();
        }else if (inGamePageWithoutTimeLimit != null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetUpButton())) {
            inGamePageWithoutTimeLimit.GetUpButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithoutTimeLimit.GetUpButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetUpButton().repaint();
        }else if (inGamePageWithoutTimeLimit != null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetDownButton())) {
            inGamePageWithoutTimeLimit.GetDownButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithoutTimeLimit.GetDownButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetDownButton().repaint();
        }else if (inGamePageWithoutTimeLimit != null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetLeftButton())) {
            inGamePageWithoutTimeLimit.GetLeftButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithoutTimeLimit.GetLeftButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetLeftButton().repaint();
        }else if (inGamePageWithoutTimeLimit != null &&inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()&& componentActivated.equals(inGamePageWithoutTimeLimit.GetRightButton())) {
            inGamePageWithoutTimeLimit.GetRightButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithoutTimeLimit.GetRightButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetRightButton().repaint();
        }else if ((boardSizeDIYPage !=null &&componentActivated.equals(boardSizeDIYPage.getSkinSwitcher()))) {
            boardSizeDIYPage.getSkinSwitcher().setBackground(null);
        }else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getSinglePlayerOption())) {
            userGameTypeChoosingPage.getSinglePlayerOption().setBackground(Color.LIGHT_GRAY);
            userGameTypeChoosingPage.getSinglePlayerOption().setVisible(true);
            userGameTypeChoosingPage.getSinglePlayerOption().repaint();
        }else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getMultiPlayerOption())) {
            userGameTypeChoosingPage.getMultiPlayerOption().setBackground(Color.LIGHT_GRAY);
            userGameTypeChoosingPage.getMultiPlayerOption().setVisible(true);
            userGameTypeChoosingPage.getMultiPlayerOption().repaint();
        }else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getRecordOption())) {
            userGameTypeChoosingPage.getRecordOption().setBackground(Color.LIGHT_GRAY);
            userGameTypeChoosingPage.getRecordOption().setVisible(true);
            userGameTypeChoosingPage.getRecordOption().repaint();
        }else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getCompetitionOption())) {
            userSingleModeChoosingPage.getCompetitionOption().setBackground(Color.LIGHT_GRAY);
            userSingleModeChoosingPage.getCompetitionOption().setVisible(true);
            userSingleModeChoosingPage.getCompetitionOption().repaint();
        }else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getPracticeOption())) {
            userSingleModeChoosingPage.getPracticeOption().setBackground(Color.LIGHT_GRAY);
            userSingleModeChoosingPage.getPracticeOption().setVisible(true);
            userSingleModeChoosingPage.getPracticeOption().repaint();
        }else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithoutTimeLimitationOption())) {
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().setVisible(true);
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().repaint();
        }else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithTimeLimitationOption())) {
            userCompetitionChoosingPage.getWithTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionChoosingPage.getWithTimeLimitationOption().setVisible(true);
            userCompetitionChoosingPage.getWithTimeLimitationOption().repaint();
        }else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getBackToMenuOption())) {
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().setVisible(true);
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().repaint();
        }else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getRestartOption())) {
            userCompetitionWithoutLimitDiePage.getRestartOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionWithoutLimitDiePage.getRestartOption().setVisible(true);
            userCompetitionWithoutLimitDiePage.getRestartOption().repaint();
        }else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getBackToMenuOption())) {
            userCompetitionWithLimitDiePage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionWithLimitDiePage.getBackToMenuOption().setVisible(true);
            userCompetitionWithLimitDiePage.getBackToMenuOption().repaint();
        }else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getRestartOption())) {
            userCompetitionWithLimitDiePage.getRestartOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionWithLimitDiePage.getRestartOption().setVisible(true);
            userCompetitionWithLimitDiePage.getRestartOption().repaint();
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
        controllingCenter = new ControllingCenter();
        ArrayList<Integer> currentInformationAboutCoordinate = new ArrayList<>();
        for (int layerInRow = 0; layerInRow < 10; layerInRow++) {
            for (int layerInColumn = 0; layerInColumn < 10; layerInColumn++) {
                if (boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].getWhetherChoosing()) {
                    currentInformationAboutCoordinate.add(boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].getxCoordinate());
                    currentInformationAboutCoordinate.add(boardSizeDIYPage.getBlockSet()[layerInRow][layerInColumn].getyCoordinate());
                }
            }
        }
        if (!currentInformationAboutCoordinate.isEmpty()){
        currentInformationAboutCoordinate = getMinimumXCoordinates(currentInformationAboutCoordinate);
        currentInformationAboutCoordinate = getMinimumYCoordinates(currentInformationAboutCoordinate);
        controllingCenter.setInformationOfAllTheCoordinateOfTheBoardUnit(currentInformationAboutCoordinate);
        controllingCenter.SetUpTheControllingCenterForDIY();}
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
    public void JudgeWhetherEndOfGameWithoutTimeLimit() {
        controllingCenter.UpdateGameValidity();
        if (!controllingCenter.getGameValidity()) {
            timerIsRunning = true;
            Timer timer = new Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timerIsRunning = false;
                    if (inGamePageWithoutTimeLimit.GetWhetherTourist()) {
                        remove(inGamePageWithoutTimeLimit);
                        inGamePageWithoutTimeLimit = null;
                        LoadTouristDiePage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    } else if (!inGamePageWithoutTimeLimit.GetWhetherTourist()){
                        remove(inGamePageWithoutTimeLimit);
                        inGamePageWithoutTimeLimit = null;
                        LoadUserCompetitionWithoutLimitDiePage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    public void JudgeWhetherWinningWithoutTimeLimit() {
        if (controllingCenter.GetWhetherReachedTheTargetScore()&&!controllingCenter.GetWhetherAlreadyShownWinningPage()) {
            winningPageIsOnShow = true;
            controllingCenter.setWhetherAlreadyShownWinningPage(true);
            timerIsRunning = true;
            Timer timer = new Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timerIsRunning = false;
                    if (inGamePageWithoutTimeLimit.GetWhetherTourist()) {
                        remove(inGamePageWithoutTimeLimit);
                        LoadTouristWinningPage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    } else if (controllingCenter.GetInCompetition()){
                        remove(inGamePageWithoutTimeLimit);
                        LoadUserWinningPage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    public void JudgeWhetherEndOfGameWithTimeLimit() {
        if (!controllingCenter.getGameValidity()) {
            timerIsRunning = true;
            Timer timer = new Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timerIsRunning = false;
                    if (inGamePageWithTimeLimit.GetWhetherTourist()) {
                        remove(inGamePageWithTimeLimit);
                        LoadTouristDiePage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    } else if (!inGamePageWithTimeLimit.GetWhetherTourist()) {
                        remove(inGamePageWithTimeLimit);
                        LoadUserCompetitionWithLimitDiePage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    private void DealWithRegistrationIssue(){
        boolean whetherRegisterSuccessfully = true;
        UserManger userManger= new UserManger();
        userRegistrationPage.GetRegistrationFunctionPanel().CleanExistingWarning();
        if (!userManger.ExamineValidityOfUserName(userRegistrationPage.GetUserName())){
            userRegistrationPage.GetRegistrationFunctionPanel().EstablishWarnForUserName(userManger.getFeedBackForUserName());
            whetherRegisterSuccessfully = false;
        }
        if (!userManger.ExamineValidityOfPassWords(userRegistrationPage.GetUserPassWord())){
            userRegistrationPage.GetRegistrationFunctionPanel().EstablishWarnForPassWords(userManger.getFeedBackForPassWords());
            whetherRegisterSuccessfully = false;
        }
        if (!userManger.ExamineValidityOfAgainPasswords(userRegistrationPage.GetUserAgainPassWord(),userRegistrationPage.GetUserPassWord())){
            userRegistrationPage.GetRegistrationFunctionPanel().EstablishWarnForAgainPassWords(userManger.getFeedbackForConfirmPasswords());
            whetherRegisterSuccessfully = false;
        }
        if (whetherRegisterSuccessfully){
            userManger.CreateDirectoryForSpecificUser(userRegistrationPage.GetUserName());
            documentReaderAndWriter = new DocumentReaderAndWriter(controllingCenter,userRegistrationPage.GetUserName());
            documentReaderAndWriter.saveUserLoginInformation(userRegistrationPage.GetUserName(),userRegistrationPage.GetUserPassWord());
            documentReaderAndWriter = null;
            this.remove(userRegistrationPage);
            userRegistrationPage = null;
            successfullyRegisteredPage = new SuccessfullyRegisteredPage(screenSize);
            this.LoadSuccessfulUserRegistrationPage();
            setFocusable(true);
            repaint();
            setVisible(true);
        }
    }
    private void DealWithLoginIssue(){
        boolean whetherLoginSuccessfully = true;
        UserManger userManger= new UserManger();
        userLoginPage.getLoginFunctionPanel().CleanExistingWarning();
        if (!userManger.ExamineValidityOfUserNameWhenLogin(userLoginPage.GetUserName())){
            userLoginPage.getLoginFunctionPanel().EstablishWarnForUserName(userManger.getFeedBackForUserNameInLogin());
            whetherLoginSuccessfully = false;
        }
        if (!userManger.ExamineValidityOfPassWordWhenLogin(userLoginPage.GetUserName(),userLoginPage.GetPassWord())){
            userLoginPage.getLoginFunctionPanel().EstablishWarnForPassWords(userManger.getFeedBackForPasswordInLogin());
            whetherLoginSuccessfully = false;
        }
        if (whetherLoginSuccessfully){
            this.SetUpTheLoginUser();
            this.remove(userLoginPage);
            userLoginPage = null;
            this.LoadUserGameTypeChoosingPage();
            repaint();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        }
    }
    private void DealWithTheDIYSetting(){
        boolean whetherDIYSucceed = true;
        UpdateTheCoordinateSetInTheControllingCenter();
        controllingCenter.setSkin(skin);
        boardSizeDIYPage.CleanExistingWarning();
        if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().isEmpty()){
                boardSizeDIYPage.EstablishWarn("You should at least choose two blocks!");
                whetherDIYSucceed = false;
            }else if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().size()<2){
                boardSizeDIYPage.EstablishWarn("You should at least choose two blocks!");
                whetherDIYSucceed = false;
            } else {
            if (boardSizeDIYPage.getWhetherTimeLimited()){
                if (boardSizeDIYPage.GetTimeLimitation().isEmpty()){
                    boardSizeDIYPage.EstablishWarn("The time limitation can not be null!");
                    whetherDIYSucceed = false;
                } else {
                    if (boardSizeDIYPage.GetTimeLimitation().length() > 5) {
                        boardSizeDIYPage.EstablishWarn("Time limitation is too long!");
                        whetherDIYSucceed = false;
                    }
                    try {
                        int timeLimit = Integer.parseInt(boardSizeDIYPage.GetTimeLimitation());
                        if (timeLimit > 3600) {
                            boardSizeDIYPage.EstablishWarn("Time limitation should be no more than 3600 seconds!");
                            whetherDIYSucceed = false;
                        } else if (timeLimit<=0) {
                            boardSizeDIYPage.EstablishWarn("Time limitation should be positive!");
                            whetherDIYSucceed = false;
                        }
                    } catch (NumberFormatException e) {
                        boardSizeDIYPage.EstablishWarn("There exists invalid characters in time limitation!");
                        whetherDIYSucceed = false;
                    }
                }
            }
        }
        if (whetherDIYSucceed){
            this.remove(boardSizeDIYPage);
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            if (boardSizeDIYPage.getWhetherTimeLimited()){
            this.LoadInGamePageForTouristWithTimeLimitation(boardSizeDIYPage.GetTimeLimit());
            }else {
                this.LoadInGamePageForTouristWithoutTimeLimitation();
            }
            boardSizeDIYPage = null;
            repaint();
            setVisible(true);
        }
    }
    private void SetUpTheLoginUser(){
        user = new User(userLoginPage.GetUserName());
    }
}