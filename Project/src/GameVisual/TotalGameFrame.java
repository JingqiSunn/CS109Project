package GameVisual;

import Competition.Client;
import Competition.ClientRunnable;
import Competition.Server;
import Competition.ServerRunnable;
import GameElement.ArtificialIntelligenceSupply;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
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
    UserPracticeModeChoosingPage userPracticeModeChoosingPage;
    UserPracticeWithoutTimeLimitModeWhetherNewPage userPracticeWithoutTimeLimitModeWhetherNewPage;
    UserPracticeWithTimeLimitModeWhetherNewPage userPracticeWithTimeLimitModeWhetherNewPage;
    UserPracticeWithoutLimitationModeChoosingPage userPracticeWithoutLimitationModeChoosingPage;
    UserPracticeWinningPage userPracticeWinningPage;
    UserPracticeWithoutLimitDiePage userPracticeWithoutLimitDiePage;
    AskingForArchivePanel askingForArchivePanel;
    UserPracticeWithLimitationModeChoosingPage userPracticeWithLimitationModeChoosingPage;
    UserPracticeWithLimitDiePage userPracticeWithLimitDiePage;
    RecordModeSelectionPage recordModeSelectionPage;
    RecordShowPageWithLimit recordShowPageWithLimit;
    WhetherNewGameRoomPage whetherNewGameRoomPage;
    CreateGameRoomPage createGameRoomPage;
    EnterGameRoomPage enterGameRoomPage;
    Thread serverThread;
    Thread clientThread;
    ClientRunnable clientRunnable;
    ServerRunnable serverRunnable;
    boolean timerIsRunning;
    boolean winningPageIsOnShow;
    boolean skin;
    public String serverName;
    public String clientName;
    public boolean whetherSuccessfullyConnected;
    public boolean whetherStartTheMultiPlayerGame;
    InGamePageWithTimeLimitForMultiUser inGamePageWithTimeLimitForMultiUser;

    RecordShowPageForWithoutLimit recordShowPageForWithoutLimit;
    SuccessfullyCreateGameRoomWaitingPage successfullyCreateGameRoomWaitingPage;
    public boolean whetherMultiGameOver;
    DiePageForMultiUser diePageForMultiUser;
    public String IPOfServer;
    public int enemyScore;
    private Server server;
    private Client client;
    ArtificialIntelligenceSupply AI;

    public TotalGameFrame() {
        AI = new ArtificialIntelligenceSupply();
        whetherMultiGameOver = false;
        whetherStartTheMultiPlayerGame = false;
        whetherSuccessfullyConnected = false;
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

    public ControllingCenter getControllingCenter() {
        return controllingCenter;
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
        userGameTypeChoosingPage = new UserGameTypeChoosingPage(screenSize, user);
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

    void LoadCreateGameRoomPage() {
        createGameRoomPage = new CreateGameRoomPage(screenSize, user);
        createGameRoomPage.setVisible(true);
        this.add(createGameRoomPage);
        createGameRoomPage.getContinueToPlay().addMouseListener(this);
        setFocusable(true);
    }

    void LoadEnterGameRoomPage() {
        enterGameRoomPage = new EnterGameRoomPage(screenSize, user);
        enterGameRoomPage.setVisible(true);
        this.add(enterGameRoomPage);
        enterGameRoomPage.getContinueToPlay().addMouseListener(this);
        setFocusable(true);
    }

    void LoadWhetherNewGameRoomPage() {
        whetherNewGameRoomPage = new WhetherNewGameRoomPage(screenSize, user);
        whetherNewGameRoomPage.setVisible(true);
        this.add(whetherNewGameRoomPage);
        whetherNewGameRoomPage.getCreateNewGameRoomOption().addMouseListener(this);
        whetherNewGameRoomPage.getEnterExistingGameRoomOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadRecordModeSelectionPage() {
        recordModeSelectionPage = new RecordModeSelectionPage(screenSize, user);
        recordModeSelectionPage.setVisible(true);
        this.add(recordModeSelectionPage);
        recordModeSelectionPage.getWithoutTimeLimitationOption().addMouseListener(this);
        recordModeSelectionPage.getWithTimeLimitationOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadDiePageForMultiUser(String content) {
        diePageForMultiUser = new DiePageForMultiUser(screenSize, content);
        diePageForMultiUser.setVisible(true);
        this.add(diePageForMultiUser);
        diePageForMultiUser.getBackOption().addMouseListener(this);
        setFocusable(true);
        repaint();
    }

    void LoadBoardSizeDIYPageWithoutTimeLimitForTourist() {
        boardSizeDIYPage = new BoardSizeDIYPage(screenSize, false);
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

    void LoadBoardSizeDIYPageWithoutTimeLimitForUserPractice() {
        boardSizeDIYPage = new BoardSizeDIYPage(screenSize, false, user, false);
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

    void LoadBoardSizeDIYPageWithTimeLimitForUserPractice() {
        boardSizeDIYPage = new BoardSizeDIYPage(screenSize, true, user, false);
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
        boardSizeDIYPage = new BoardSizeDIYPage(screenSize, true);
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
        inGamePageWithoutTimeLimit = new InGamePageWithoutTimeLimit(screenSize, controllingCenter, true);
        inGamePageWithoutTimeLimit.addKeyListener(this);
        inGamePageWithoutTimeLimit.setVisible(true);
        inGamePageWithoutTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithoutTimeLimit);
        setFocusable(true);
    }

    void LoadAskingForArchivePanel() {
        askingForArchivePanel = new AskingForArchivePanel(screenSize, user);
        askingForArchivePanel.addKeyListener(this);
        askingForArchivePanel.setVisible(true);
        askingForArchivePanel.getContinueToPlay().addMouseListener(this);
        this.add(askingForArchivePanel);
        setFocusable(true);
    }

    void LoadInGamePageForUserWithoutTimeLimitationCompetition() {
        user.UpdateUserInformationForCompetition();
        user.UpdateTheAverageScoreForStartOfGameCompetitionWithoutTimeLimit();
        user.addOneGameTotalGameNumberForCompetitionWithoutLimit();
        inGamePageWithoutTimeLimit = new InGamePageWithoutTimeLimit(screenSize, controllingCenter, false, user, true, false);
        inGamePageWithoutTimeLimit.addKeyListener(this);
        inGamePageWithoutTimeLimit.setVisible(true);
        inGamePageWithoutTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithoutTimeLimit);
        setFocusable(true);
    }

    void LoadInGamePageForUserWithoutTimeLimitationPractice() {
        inGamePageWithoutTimeLimit = new InGamePageWithoutTimeLimit(screenSize, controllingCenter, false, user, false, false);
        inGamePageWithoutTimeLimit.addKeyListener(this);
        inGamePageWithoutTimeLimit.setVisible(true);
        inGamePageWithoutTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithoutTimeLimit);
        setFocusable(true);
    }

    void LoadInGamePageForTouristWithTimeLimitation(int timeLimit) {
        inGamePageWithTimeLimit = new InGamePageWithTimeLimit(screenSize, controllingCenter, true, timeLimit, this);
        inGamePageWithTimeLimit.addKeyListener(this);
        inGamePageWithTimeLimit.setVisible(true);
        inGamePageWithTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithTimeLimit);
        setFocusable(true);
    }

    void LoadInGamePageForMultiUserWithTimeLimitation() {
        controllingCenter = new ControllingCenter();
        this.UpdateTheCoordinateSetInTheControllingCenterForFour();
        controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
        inGamePageWithTimeLimitForMultiUser = new InGamePageWithTimeLimitForMultiUser(screenSize, controllingCenter, true, 180, this, successfullyCreateGameRoomWaitingPage.GetWhetherServer());
        successfullyCreateGameRoomWaitingPage = null;
        inGamePageWithTimeLimitForMultiUser.addKeyListener(this);
        inGamePageWithTimeLimitForMultiUser.setVisible(true);
        inGamePageWithTimeLimitForMultiUser.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithTimeLimitForMultiUser);
        setFocusable(true);
        repaint();
    }

    void LoadInGamePageForUserWithTimeLimitationCompetition(int timeLimit) {
        user.UpdateUserInformationForCompetition();
        user.UpdateTheAverageScoreForStartOfGameCompetitionWithTimeLimit();
        user.addOneGameTotalGameNumberForCompetitionWithLimit();
        inGamePageWithTimeLimit = new InGamePageWithTimeLimit(screenSize, controllingCenter, false, timeLimit, this, user, true);
        inGamePageWithTimeLimit.addKeyListener(this);
        inGamePageWithTimeLimit.setVisible(true);
        inGamePageWithTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithTimeLimit);
        setFocusable(true);
    }

    void LoadInGamePageForUserWithTimeLimitationPractice(int timeLimit) {
        inGamePageWithTimeLimit = new InGamePageWithTimeLimit(screenSize, controllingCenter, false, timeLimit, this, user, false);
        inGamePageWithTimeLimit.addKeyListener(this);
        inGamePageWithTimeLimit.setVisible(true);
        inGamePageWithTimeLimit.getButtonControllerSwitch().addMouseListener(this);
        this.add(inGamePageWithTimeLimit);
        setFocusable(true);
    }

    void LoadTouristDiePage() {
        touristDiePage = new TouristDiePage(screenSize, controllingCenter, 0);
        touristDiePage.setVisible(true);
        this.add(touristDiePage);
        touristDiePage.getBackToMenuOption().addMouseListener(this);
        touristDiePage.getRestartOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserCompetitionWithoutLimitDiePage() {
        user.UpdateTheAverageScoreForEndOfGameCompetitionWithoutTimeLimit(controllingCenter.getCurrentGameScore());
        user.UpdateBestFiveScoreForCompetitionWithoutTimeLimit(controllingCenter.getCurrentGameScore());
        if (controllingCenter.getCurrentGameScore() >= 7000) {
            user.IncreaseSevenThousandTimeInCompetitionWithoutTimeLimit();
        }
        if (controllingCenter.getCurrentGameScore() >= 14000) {
            user.IncreaseFourteenThousandTimeInCompetitionWithoutTimeLimit();
        }
        userCompetitionWithoutLimitDiePage = new UserCompetitionWithoutLimitDiePage(screenSize, controllingCenter, user);
        userCompetitionWithoutLimitDiePage.setVisible(true);
        this.add(userCompetitionWithoutLimitDiePage);
        userCompetitionWithoutLimitDiePage.getBackToMenuOption().addMouseListener(this);
        userCompetitionWithoutLimitDiePage.getRestartOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserPracticeWithoutLimitDiePage() {
        userPracticeWithoutLimitDiePage = new UserPracticeWithoutLimitDiePage(screenSize, controllingCenter, 0, user);
        userPracticeWithoutLimitDiePage.setVisible(true);
        this.add(userPracticeWithoutLimitDiePage);
        userPracticeWithoutLimitDiePage.getBackToMenuOption().addMouseListener(this);
        userPracticeWithoutLimitDiePage.getRestartOption().addMouseListener(this);
        setFocusable(true);
        user.DeleteCompleteArchive(controllingCenter.getArchiveName());
    }

    void LoadRecordShowPageForWithoutLimit() {
        recordShowPageForWithoutLimit = new RecordShowPageForWithoutLimit(screenSize, user);
        recordShowPageForWithoutLimit.setVisible(true);
        this.add(recordShowPageForWithoutLimit);
        setFocusable(true);
    }

    void LoadRecordShowPageForWithLimit() {
        recordShowPageWithLimit = new RecordShowPageWithLimit(screenSize, user);
        recordShowPageWithLimit.setVisible(true);
        this.add(recordShowPageWithLimit);
        setFocusable(true);
    }

    void LoadUserCompetitionWithLimitDiePage() {
        user.UpdateBestFiveScoreForCompetitionWithTimeLimit(controllingCenter.getCurrentGameScore());
        user.UpdateTheAverageScoreForEndOfGameCompetitionWithTimeLimit(controllingCenter.getCurrentGameScore());
        userCompetitionWithLimitDiePage = new UserCompetitionWithLimitDiePage(screenSize, controllingCenter, user);
        userCompetitionWithLimitDiePage.setVisible(true);
        this.add(userCompetitionWithLimitDiePage);
        userCompetitionWithLimitDiePage.getBackToMenuOption().addMouseListener(this);
        userCompetitionWithLimitDiePage.getRestartOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserPracticeWithLimitDiePage() {
        userPracticeWithLimitDiePage = new UserPracticeWithLimitDiePage(screenSize, controllingCenter, user);
        userPracticeWithLimitDiePage.setVisible(true);
        this.add(userPracticeWithLimitDiePage);
        userPracticeWithLimitDiePage.getBackToMenuOption().addMouseListener(this);
        userPracticeWithLimitDiePage.getRestartOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadTouristWinningPage() {
        touristWinningPage = new TouristWinningPage(screenSize, controllingCenter);
        touristWinningPage.setVisible(true);
        this.add(touristWinningPage);
        touristWinningPage.getBackToMenuOption().addMouseListener(this);
        touristWinningPage.getContinueOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserPracticeWinningPage() {
        user.SetWhetherWonToBeTrue(controllingCenter.getArchiveName());
        userPracticeWinningPage = new UserPracticeWinningPage(screenSize, controllingCenter, user);
        userPracticeWinningPage.setVisible(true);
        this.add(userPracticeWinningPage);
        userPracticeWinningPage.getBackToMenuOption().addMouseListener(this);
        userPracticeWinningPage.getContinueOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserWinningPage() {
        user.IncreaseWinningTimeInCompetitionWithoutTimeLimit();
        userWinningPage = new UserWinningPage(screenSize, controllingCenter, user);
        userWinningPage.setVisible(true);
        this.add(userWinningPage);
        userWinningPage.getBackToMenuOption().addMouseListener(this);
        userWinningPage.getContinueOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserLoginPage() {
        userLoginPage = new UserLoginPage(screenSize);
        userLoginPage.setVisible(true);
        this.add(userLoginPage);
        userLoginPage.GetClickHereToRegister().addMouseListener(this);
        userLoginPage.GetLoginConfirmPanel().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserRegistrationPage() {
        userRegistrationPage = new UserRegistrationPage(screenSize);
        userRegistrationPage.setVisible(true);
        this.add(userRegistrationPage);
        userRegistrationPage.GetClickHereToLoginPanel().addMouseListener(this);
        userRegistrationPage.GetRegistrationConfirmPanel().addMouseListener(this);
        setFocusable(true);
    }

    void LoadSuccessfulUserRegistrationPage() {
        this.add(successfullyRegisteredPage);
        setFocusable(true);
        this.addMouseListener(this);
        this.setFocusable(true);
        successfullyRegisteredPage.GetBackToMenu().addMouseListener(this);
        successfullyRegisteredPage.GetToLogin().addMouseListener(this);
        this.setVisible(true);
        repaint();
    }

    void LoadTimeLimitChoosingPage() {
        timeLimitChoosingPage = new TimeLimitChoosingPage(screenSize);
        timeLimitChoosingPage.setVisible(true);
        timeLimitChoosingPage.getThreeMinutesOption().addMouseListener(this);
        timeLimitChoosingPage.getSixMinutesOption().addMouseListener(this);
        timeLimitChoosingPage.getDIYOption().addMouseListener(this);
        this.add(timeLimitChoosingPage);
        setFocusable(true);
    }

    void LoadUserSingleModeChoosingPage() {
        userSingleModeChoosingPage = new UserSingleModeChoosingPage(screenSize, user);
        userSingleModeChoosingPage.setVisible(true);
        this.add(userSingleModeChoosingPage);
        userSingleModeChoosingPage.getCompetitionOption().addMouseListener(this);
        userSingleModeChoosingPage.getPracticeOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserCompetitionChoosingPage() {
        userCompetitionChoosingPage = new UserCompetitionChoosingPage(screenSize, user);
        userCompetitionChoosingPage.setVisible(true);
        this.add(userCompetitionChoosingPage);
        userCompetitionChoosingPage.getWithoutTimeLimitationOption().addMouseListener(this);
        userCompetitionChoosingPage.getWithTimeLimitationOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserPracticeModeChoosingPage() {
        userPracticeModeChoosingPage = new UserPracticeModeChoosingPage(screenSize, user);
        userPracticeModeChoosingPage.setVisible(true);
        this.add(userPracticeModeChoosingPage);
        userPracticeModeChoosingPage.getWithoutTimeLimitationOption().addMouseListener(this);
        userPracticeModeChoosingPage.getWithTimeLimitationOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserPracticeWithoutTimeLimitModeWhetherNewPage() {
        userPracticeWithoutTimeLimitModeWhetherNewPage = new UserPracticeWithoutTimeLimitModeWhetherNewPage(screenSize, user);
        userPracticeWithoutTimeLimitModeWhetherNewPage.setVisible(true);
        this.add(userPracticeWithoutTimeLimitModeWhetherNewPage);
        userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption().addMouseListener(this);
        userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserPracticeWithTimeLimitModeWhetherNewPage() {
        userPracticeWithTimeLimitModeWhetherNewPage = new UserPracticeWithTimeLimitModeWhetherNewPage(screenSize, user);
        userPracticeWithTimeLimitModeWhetherNewPage.setVisible(true);
        this.add(userPracticeWithTimeLimitModeWhetherNewPage);
        userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption().addMouseListener(this);
        userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserPracticeWithoutLimitationModeChoosingPage() {
        userPracticeWithoutLimitationModeChoosingPage = new UserPracticeWithoutLimitationModeChoosingPage(screenSize, user);
        userPracticeWithoutLimitationModeChoosingPage.setVisible(true);
        this.add(userPracticeWithoutLimitationModeChoosingPage);
        userPracticeWithoutLimitationModeChoosingPage.getThreeOption().addMouseListener(this);
        userPracticeWithoutLimitationModeChoosingPage.getFourOption().addMouseListener(this);
        userPracticeWithoutLimitationModeChoosingPage.getDIYOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadUserPracticeWithLimitationModeChoosingPage() {
        userPracticeWithLimitationModeChoosingPage = new UserPracticeWithLimitationModeChoosingPage(screenSize, user);
        userPracticeWithLimitationModeChoosingPage.setVisible(true);
        this.add(userPracticeWithLimitationModeChoosingPage);
        userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption().addMouseListener(this);
        userPracticeWithLimitationModeChoosingPage.getSixMinutesOption().addMouseListener(this);
        userPracticeWithLimitationModeChoosingPage.getDIYOption().addMouseListener(this);
        setFocusable(true);
    }

    void LoadSuccessfullyCreateGameRoomWaitingPageForServer(String IPAddress) {
        successfullyCreateGameRoomWaitingPage = new SuccessfullyCreateGameRoomWaitingPage(IPAddress, screenSize, this, user);
        successfullyCreateGameRoomWaitingPage.setVisible(true);
        this.add(successfullyCreateGameRoomWaitingPage);
        successfullyCreateGameRoomWaitingPage.getOpenPanel().addMouseListener(this);
        setFocusable(true);
    }

    void LoadSuccessfullyCreateGameRoomWaitingPageForClient(String IPAddress, String serverName) {
        successfullyCreateGameRoomWaitingPage = new SuccessfullyCreateGameRoomWaitingPage(IPAddress, screenSize, this);
        successfullyCreateGameRoomWaitingPage.setVisible(true);
        this.add(successfullyCreateGameRoomWaitingPage);
        successfullyCreateGameRoomWaitingPage.getContinuePanel().addMouseListener(this);
        setFocusable(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyBeingActivated = e.getKeyCode();
        if (keyBeingActivated == KeyEvent.VK_ESCAPE) {
            this.outOfFullScreen();
            this.setVisible(true);
        } else if (userRegistrationPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userRegistrationPage);
            userRegistrationPage = null;
            this.LoadLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userLoginPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userLoginPage);
            userLoginPage = null;
            this.LoadLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userGameTypeChoosingPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userGameTypeChoosingPage);
            userGameTypeChoosingPage = null;
            user = null;
            this.LoadLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userSingleModeChoosingPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userSingleModeChoosingPage);
            userSingleModeChoosingPage = null;
            this.LoadUserGameTypeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (whetherNewGameRoomPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(whetherNewGameRoomPage);
            whetherNewGameRoomPage = null;
            this.LoadUserGameTypeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (recordModeSelectionPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(recordModeSelectionPage);
            recordModeSelectionPage = null;
            this.LoadUserGameTypeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (recordShowPageWithLimit != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(recordShowPageWithLimit);
            recordShowPageWithLimit = null;
            this.LoadRecordModeSelectionPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (recordShowPageForWithoutLimit != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(recordShowPageForWithoutLimit);
            recordShowPageForWithoutLimit = null;
            this.LoadRecordModeSelectionPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userCompetitionChoosingPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userCompetitionChoosingPage);
            userCompetitionChoosingPage = null;
            this.LoadUserSingleModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeModeChoosingPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userPracticeModeChoosingPage);
            userPracticeModeChoosingPage = null;
            this.LoadUserSingleModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userPracticeWithoutLimitationModeChoosingPage);
            userPracticeWithoutLimitationModeChoosingPage = null;
            this.LoadUserPracticeWithoutTimeLimitModeWhetherNewPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithLimitationModeChoosingPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userPracticeWithLimitationModeChoosingPage);
            userPracticeWithLimitationModeChoosingPage = null;
            this.LoadUserPracticeWithTimeLimitModeWhetherNewPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithoutTimeLimitModeWhetherNewPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userPracticeWithoutTimeLimitModeWhetherNewPage);
            userPracticeWithoutTimeLimitModeWhetherNewPage = null;
            this.LoadUserPracticeModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithTimeLimitModeWhetherNewPage != null && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
            this.remove(userPracticeWithTimeLimitModeWhetherNewPage);
            userPracticeWithTimeLimitModeWhetherNewPage = null;
            this.LoadUserPracticeModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (inGamePageWithoutTimeLimit != null && !winningPageIsOnShow && keyBeingActivated == KeyEvent.VK_UP && !timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            boolean whetherToSave = false;
            if (!inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && controllingCenter.getCurrentPlayingBoard().getAvailableDirectionSet()[0] == 1) {
                whetherToSave = true;
            }
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            if (whetherToSave) {
                user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            }
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && !winningPageIsOnShow && keyBeingActivated == KeyEvent.VK_DOWN && !timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            boolean whetherToSave = false;
            if (!inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && controllingCenter.getCurrentPlayingBoard().getAvailableDirectionSet()[1] == 1) {
                whetherToSave = true;
            }
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            if (whetherToSave) {
                user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            }
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && !winningPageIsOnShow && keyBeingActivated == KeyEvent.VK_LEFT && !timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            boolean whetherToSave = false;
            if (!inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && controllingCenter.getCurrentPlayingBoard().getAvailableDirectionSet()[2] == 1) {
                whetherToSave = true;
            }
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            if (whetherToSave) {
                user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            }
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && !winningPageIsOnShow && keyBeingActivated == KeyEvent.VK_RIGHT && !timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            boolean whetherToSave = false;
            if (!inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && controllingCenter.getCurrentPlayingBoard().getAvailableDirectionSet()[3] == 1) {
                whetherToSave = true;
            }
            controllingCenter.RightAction();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            if (whetherToSave) {
                user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            }
            controllingCenter.UpdateGameValidity();
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && keyBeingActivated == KeyEvent.VK_R && !timerIsRunning) {
            inGamePageWithoutTimeLimit.RestartTheGame();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_UP && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_DOWN && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_LEFT && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_RIGHT && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.RightAction();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            controllingCenter.UpdateGameValidity();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && keyBeingActivated == KeyEvent.VK_R && !timerIsRunning) {
            inGamePageWithTimeLimit.RestartTheGame();
        } else if (inGamePageWithTimeLimit != null &&!inGamePageWithTimeLimit.isWhetherCompetition()&& e.isControlDown() && e.getKeyCode() == KeyEvent.VK_A && !timerIsRunning) {
            String direction = AI.mostScoresEarned(controllingCenter);
            if (direction == "Down"){
                this.JudgeWhetherEndOfGameWithTimeLimit();
                controllingCenter.UpdateTheAvailableDirectionSet();
                controllingCenter.DownAction();
                controllingCenter.UpdateGameValidity();
                inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
                this.repaint();
                this.JudgeWhetherEndOfGameWithTimeLimit();
            } else if (direction == "Up") {
                this.JudgeWhetherEndOfGameWithTimeLimit();
                controllingCenter.UpdateTheAvailableDirectionSet();
                controllingCenter.UpAction();
                controllingCenter.UpdateGameValidity();
                inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
                this.repaint();
                this.JudgeWhetherEndOfGameWithTimeLimit();
            }else if (direction == "Left"){
                this.JudgeWhetherEndOfGameWithTimeLimit();
                controllingCenter.UpdateTheAvailableDirectionSet();
                controllingCenter.LeftAction();
                controllingCenter.UpdateGameValidity();
                inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
                this.repaint();
                this.JudgeWhetherEndOfGameWithTimeLimit();
            } else if (direction == "Right") {
                this.JudgeWhetherEndOfGameWithTimeLimit();
                controllingCenter.UpdateTheAvailableDirectionSet();
                controllingCenter.RightAction();
                inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
                controllingCenter.UpdateGameValidity();
                this.repaint();
                this.JudgeWhetherEndOfGameWithTimeLimit();
            }
        }else if (inGamePageWithTimeLimitForMultiUser != null && keyBeingActivated == KeyEvent.VK_UP && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimitForMultiUser();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimitForMultiUser.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimitForMultiUser();
        } else if (inGamePageWithTimeLimitForMultiUser != null && keyBeingActivated == KeyEvent.VK_DOWN && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimitForMultiUser();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimitForMultiUser.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimitForMultiUser();
        } else if (inGamePageWithTimeLimitForMultiUser != null && keyBeingActivated == KeyEvent.VK_LEFT && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimitForMultiUser();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimitForMultiUser.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimitForMultiUser();
        } else if (inGamePageWithTimeLimitForMultiUser != null && keyBeingActivated == KeyEvent.VK_RIGHT && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimitForMultiUser();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.RightAction();
            inGamePageWithTimeLimitForMultiUser.UpdateBlockUnitsInGame();
            controllingCenter.UpdateGameValidity();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimitForMultiUser();
        } else if (inGamePageWithoutTimeLimit != null && !inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W) {
            user.GoingOneStepBackWards(controllingCenter.getArchiveName(), controllingCenter);
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            controllingCenter.UpdateGameValidity();
            this.repaint();
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
        } else if (loginPage != null && componentActivated.equals(loginPage.getRegistrationOption())) {
            remove(loginPage);
            loginPage = null;
            this.LoadUserRegistrationPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (loginPage != null && componentActivated.equals(loginPage.getTouristOption())) {
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
        } else if (modeChoosingPage != null && componentActivated.equals(modeChoosingPage.getLimitedTimeOption())) {
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
            this.LoadBoardSizeDIYPageWithoutTimeLimitForTourist();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getDIYOption())) {
            remove(userPracticeWithoutLimitationModeChoosingPage);
            userPracticeWithoutLimitationModeChoosingPage = null;
            this.LoadBoardSizeDIYPageWithoutTimeLimitForUserPractice();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getDIYOption())) {
            remove(userPracticeWithLimitationModeChoosingPage);
            userPracticeWithLimitationModeChoosingPage = null;
            this.LoadBoardSizeDIYPageWithTimeLimitForUserPractice();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getThreeOption())) {
            remove(userPracticeWithoutLimitationModeChoosingPage);
            userPracticeWithoutLimitationModeChoosingPage = null;
            this.DealWithDefaultThreeInPractice();
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getFourOption())) {
            remove(userPracticeWithoutLimitationModeChoosingPage);
            userPracticeWithoutLimitationModeChoosingPage = null;
            this.DealWithDefaultFourInPractice();
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
        } else if (touristDiePage != null && componentActivated.equals(touristDiePage.getBackToMenuOption())) {
            skin = false;
            if (inGamePageWithTimeLimit != null) {
                inGamePageWithTimeLimit = null;
                controllingCenter = new ControllingCenter();
                this.remove(touristDiePage);
                touristDiePage = null;
                this.LoadTimeLimitChoosingPage();
                this.addMouseListener(this);
                this.setFocusable(true);
                repaint();
                this.setVisible(true);
            } else {
                controllingCenter = new ControllingCenter();
                this.remove(touristDiePage);
                touristDiePage = null;
                this.LoadBoardSizeChoosingPage();
                this.addMouseListener(this);
                this.setFocusable(true);
                repaint();
                this.setVisible(true);
            }
        } else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getBackToMenuOption())) {
            skin = false;
            controllingCenter = new ControllingCenter();
            this.remove(userCompetitionWithoutLimitDiePage);
            userCompetitionWithoutLimitDiePage = null;
            this.LoadUserCompetitionChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getBackToMenuOption())) {
            skin = false;
            controllingCenter = new ControllingCenter();
            this.remove(userCompetitionWithLimitDiePage);
            userCompetitionWithoutLimitDiePage = null;
            inGamePageWithTimeLimit = null;
            this.LoadUserCompetitionChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithoutLimitDiePage != null && componentActivated.equals(userPracticeWithoutLimitDiePage.getBackToMenuOption())) {
            skin = false;
            controllingCenter = new ControllingCenter();
            this.remove(userPracticeWithoutLimitDiePage);
            userPracticeWithoutLimitDiePage = null;
            this.LoadUserPracticeWithoutLimitationModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithLimitDiePage != null && componentActivated.equals(userPracticeWithLimitDiePage.getBackToMenuOption())) {
            skin = false;
            controllingCenter = new ControllingCenter();
            this.remove(userPracticeWithLimitDiePage);
            userPracticeWithLimitDiePage = null;
            this.LoadUserPracticeWithLimitationModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (touristDiePage != null && componentActivated.equals(touristDiePage.getRestartOption())) {
            if (inGamePageWithTimeLimit == null) {
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
        } else if (userPracticeWithLimitDiePage != null && componentActivated.equals(userPracticeWithLimitDiePage.getRestartOption())) {
            int originalTimeLimit = inGamePageWithTimeLimit.getOriginalTimeLimit();
            this.remove(userPracticeWithLimitDiePage);
            userPracticeWithLimitDiePage = null;
            inGamePageWithTimeLimit = null;
            controllingCenter.CleanThePlayingBoardForRestart();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            controllingCenter.UpdateGameValidity();
            this.LoadInGamePageForUserWithTimeLimitationPractice(originalTimeLimit);
            repaint();
            setVisible(true);
        } else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getRestartOption())) {
            user.UpdateUserInformationForCompetition();
            this.remove(userCompetitionWithoutLimitDiePage);
            userCompetitionWithoutLimitDiePage = null;
            controllingCenter.setWhetherReachedTheTargetScore(false);
            controllingCenter.setWhetherAlreadyShownWinningPage(false);
            controllingCenter.CleanThePlayingBoardForRestart();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForUserWithoutTimeLimitationCompetition();
            repaint();
            setVisible(true);
        } else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getRestartOption())) {
            user.UpdateUserInformationForCompetition();
            int originalTimeLimit = inGamePageWithTimeLimit.getOriginalTimeLimit();
            this.remove(userCompetitionWithLimitDiePage);
            userCompetitionWithLimitDiePage = null;
            inGamePageWithTimeLimit = null;
            controllingCenter.CleanThePlayingBoardForRestart();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            controllingCenter.UpdateGameValidity();
            this.LoadInGamePageForUserWithTimeLimitationCompetition(originalTimeLimit);
            repaint();
            setVisible(true);
        } else if (userPracticeWithoutLimitDiePage != null && componentActivated.equals(userPracticeWithoutLimitDiePage.getRestartOption())) {
            this.remove(userPracticeWithoutLimitDiePage);
            userPracticeWithoutLimitDiePage = null;
            controllingCenter.setWhetherAlreadyShownWinningPage(false);
            controllingCenter.setWhetherReachedTheTargetScore(false);
            controllingCenter.CleanThePlayingBoardForRestart();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            controllingCenter.UpdateGameValidity();
            user.SaveGameBoardToASpecificArchivePracticeWithoutTimeLimit(controllingCenter.getArchiveName(), controllingCenter);
            user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            this.LoadInGamePageForUserWithoutTimeLimitationPractice();
            repaint();
            setVisible(true);
        } else if (userLoginPage != null && componentActivated.equals(userLoginPage.GetClickHereToRegister())) {
            remove(userLoginPage);
            userLoginPage = null;
            this.LoadUserRegistrationPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userRegistrationPage != null && componentActivated.equals(userRegistrationPage.GetClickHereToLoginPanel())) {
            remove(userRegistrationPage);
            userRegistrationPage = null;
            this.LoadUserLoginPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userRegistrationPage != null && componentActivated.equals(userRegistrationPage.GetRegistrationConfirmPanel())) {
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
        } else if (userLoginPage != null && componentActivated.equals(userLoginPage.GetLoginConfirmPanel())) {
            this.DealWithLoginIssue();
        } else if (boardSizeDIYPage != null && !boardSizeDIYPage.getWhetherTimeLimited() && componentActivated.equals(boardSizeDIYPage.GetContinueButton())) {
            this.DealWithTheDIYSetting();
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getDIYOption())) {
            remove(timeLimitChoosingPage);
            timeLimitChoosingPage = null;
            this.LoadBoardSizeDIYPageWithTimeLimit();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (boardSizeDIYPage != null && boardSizeDIYPage.getWhetherTimeLimited() && componentActivated.equals(boardSizeDIYPage.GetContinueButton())) {
            this.DealWithTheDIYSetting();
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getThreeMinutesOption())) {
            remove(timeLimitChoosingPage);
            timeLimitChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTouristWithTimeLimitation(180);
            repaint();
            setVisible(true);
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption())) {
            remove(userPracticeWithLimitationModeChoosingPage);
            userPracticeWithLimitationModeChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            controllingCenter.setWhetherTimeLimitationMode(true);
            this.LoadInGamePageForUserWithTimeLimitationPractice(180);
            repaint();
            setVisible(true);
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getSixMinutesOption())) {
            remove(userPracticeWithLimitationModeChoosingPage);
            userPracticeWithLimitationModeChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            controllingCenter.setWhetherTimeLimitationMode(true);
            this.LoadInGamePageForUserWithTimeLimitationPractice(360);
            repaint();
            setVisible(true);
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getSixMinutesOption())) {
            remove(timeLimitChoosingPage);
            timeLimitChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForTouristWithTimeLimitation(360);
            repaint();
            setVisible(true);
        } else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithTimeLimitationOption())) {
            remove(userCompetitionChoosingPage);
            userCompetitionChoosingPage = null;
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.setInCompetition(true);
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            this.LoadInGamePageForUserWithTimeLimitationCompetition(180);
            repaint();
            setVisible(true);
        } else if ((inGamePageWithTimeLimit != null && !inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()) && componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.LoadButtonController();
            inGamePageWithTimeLimit.GetUpButton().addMouseListener(this);
            inGamePageWithTimeLimit.GetDownButton().addMouseListener(this);
            inGamePageWithTimeLimit.GetLeftButton().addMouseListener(this);
            inGamePageWithTimeLimit.GetRightButton().addMouseListener(this);
            repaint();
            setVisible(true);
        } else if (inGamePageWithoutTimeLimit != null && !inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.LoadButtonController();
            inGamePageWithoutTimeLimit.GetUpButton().addMouseListener(this);
            inGamePageWithoutTimeLimit.GetDownButton().addMouseListener(this);
            inGamePageWithoutTimeLimit.GetLeftButton().addMouseListener(this);
            inGamePageWithoutTimeLimit.GetRightButton().addMouseListener(this);
            repaint();
            setVisible(true);
        } else if ((inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()) && componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.CleanButtonController();
            repaint();
            setVisible(true);
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.CleanButtonController();
            repaint();
            setVisible(true);
        } else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getBackToMenuOption())) {
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
        } else if (userWinningPage != null && componentActivated.equals(userWinningPage.getBackToMenuOption())) {
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
        } else if (userPracticeWinningPage != null && componentActivated.equals(userPracticeWinningPage.getBackToMenuOption())) {
            skin = false;
            inGamePageWithoutTimeLimit = null;
            controllingCenter = new ControllingCenter();
            this.remove(userPracticeWinningPage);
            userPracticeWinningPage = null;
            this.LoadUserPracticeWithoutLimitationModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
            winningPageIsOnShow = false;
        } else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getContinueOption())) {
            this.remove(touristWinningPage);
            touristWinningPage = null;
            this.add(inGamePageWithoutTimeLimit);
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
            winningPageIsOnShow = false;
        } else if (userWinningPage != null && componentActivated.equals(userWinningPage.getContinueOption())) {
            this.remove(userWinningPage);
            userWinningPage = null;
            this.add(inGamePageWithoutTimeLimit);
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
            winningPageIsOnShow = false;
        } else if (userPracticeWinningPage != null && componentActivated.equals(userPracticeWinningPage.getContinueOption())) {
            this.remove(userPracticeWinningPage);
            userPracticeWinningPage = null;
            this.add(inGamePageWithoutTimeLimit);
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
            winningPageIsOnShow = false;
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetUpButton()) && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetDownButton()) && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetLeftButton()) && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetRightButton()) && !timerIsRunning) {
            this.JudgeWhetherEndOfGameWithTimeLimit();
            controllingCenter.UpdateTheAvailableDirectionSet();
            controllingCenter.RightAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithTimeLimit.UpdateBlockUnitsInGame();
            this.repaint();
            this.JudgeWhetherEndOfGameWithTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && !winningPageIsOnShow && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetUpButton()) && !timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            boolean whetherToSave = false;
            if (!inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && controllingCenter.getCurrentPlayingBoard().getAvailableDirectionSet()[0] == 1) {
                whetherToSave = true;
            }
            controllingCenter.UpAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            if (whetherToSave) {
                user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            }
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && !winningPageIsOnShow && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetDownButton()) && !timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            boolean whetherToSave = false;
            if (!inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && controllingCenter.getCurrentPlayingBoard().getAvailableDirectionSet()[1] == 1) {
                whetherToSave = true;
            }
            controllingCenter.DownAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            if (whetherToSave) {
                user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            }
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && !winningPageIsOnShow && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetLeftButton()) && !timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            boolean whetherToSave = false;
            if (!inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && controllingCenter.getCurrentPlayingBoard().getAvailableDirectionSet()[2] == 1) {
                whetherToSave = true;
            }
            controllingCenter.LeftAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            if (whetherToSave) {
                user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            }
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if (inGamePageWithoutTimeLimit != null && !winningPageIsOnShow && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetRightButton()) && !timerIsRunning) {
            controllingCenter.UpdateTheAvailableDirectionSet();
            boolean whetherToSave = false;
            if (!inGamePageWithoutTimeLimit.isWhetherTourist() && !inGamePageWithoutTimeLimit.getWhetherCompetition() && controllingCenter.getCurrentPlayingBoard().getAvailableDirectionSet()[3] == 1) {
                whetherToSave = true;
            }
            controllingCenter.RightAction();
            controllingCenter.UpdateGameValidity();
            inGamePageWithoutTimeLimit.UpdateBlockUnitsInGame();
            if (whetherToSave) {
                user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
            }
            this.repaint();
            this.JudgeWhetherWinningWithoutTimeLimit();
            this.JudgeWhetherEndOfGameWithoutTimeLimit();
        } else if ((boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.getSkinSwitcher())) && !skin) {
            skin = true;
        } else if ((boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.getSkinSwitcher())) && skin) {
            skin = false;
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getSinglePlayerOption())) {
            remove(userGameTypeChoosingPage);
            userGameTypeChoosingPage = null;
            this.LoadUserSingleModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getCompetitionOption())) {
            remove(userSingleModeChoosingPage);
            userSingleModeChoosingPage = null;
            this.LoadUserCompetitionChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getPracticeOption())) {
            remove(userSingleModeChoosingPage);
            userSingleModeChoosingPage = null;
            this.LoadUserPracticeModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeModeChoosingPage != null && componentActivated.equals(userPracticeModeChoosingPage.getWithoutTimeLimitationOption())) {
            remove(userPracticeModeChoosingPage);
            userPracticeModeChoosingPage = null;
            this.LoadUserPracticeWithoutTimeLimitModeWhetherNewPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeModeChoosingPage != null && componentActivated.equals(userPracticeModeChoosingPage.getWithTimeLimitationOption())) {
            remove(userPracticeModeChoosingPage);
            userPracticeModeChoosingPage = null;
            this.LoadUserPracticeWithLimitationModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithoutTimeLimitationOption())) {
            remove(userCompetitionChoosingPage);
            userCompetitionChoosingPage = null;
            user.UpdateUserInformationForCompetition();
            UpdateTheCoordinateSetInTheControllingCenterForFour();
            controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
            controllingCenter.setInCompetition(true);
            this.LoadInGamePageForUserWithoutTimeLimitationCompetition();
            repaint();
            setVisible(true);
        } else if (userPracticeWithoutTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption())) {
            remove(userPracticeWithoutTimeLimitModeWhetherNewPage);
            userPracticeWithoutTimeLimitModeWhetherNewPage = null;
            this.LoadUserPracticeWithoutLimitationModeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userPracticeWithoutTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption())) {
            remove(userPracticeWithoutTimeLimitModeWhetherNewPage);
            userPracticeWithoutTimeLimitModeWhetherNewPage = null;
            this.LoadAskingForArchivePanel();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (askingForArchivePanel != null && componentActivated.equals(askingForArchivePanel.getContinueToPlay())) {
            this.DealWithArchiveInput();
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getRecordOption())) {
            remove(userGameTypeChoosingPage);
            userGameTypeChoosingPage = null;
            this.LoadRecordModeSelectionPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (recordModeSelectionPage != null && componentActivated.equals(recordModeSelectionPage.getWithoutTimeLimitationOption())) {
            remove(recordModeSelectionPage);
            recordModeSelectionPage = null;
            this.LoadRecordShowPageForWithoutLimit();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (recordModeSelectionPage != null && componentActivated.equals(recordModeSelectionPage.getWithTimeLimitationOption())) {
            remove(recordModeSelectionPage);
            recordModeSelectionPage = null;
            this.LoadRecordShowPageForWithLimit();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getMultiPlayerOption())) {
            remove(userGameTypeChoosingPage);
            userGameTypeChoosingPage = null;
            this.LoadWhetherNewGameRoomPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (whetherNewGameRoomPage != null && componentActivated.equals(whetherNewGameRoomPage.getCreateNewGameRoomOption())) {
            this.OpenGameRoom();
        } else if (whetherNewGameRoomPage != null && componentActivated.equals(whetherNewGameRoomPage.getEnterExistingGameRoomOption())) {
            remove(whetherNewGameRoomPage);
            whetherNewGameRoomPage = null;
            this.LoadEnterGameRoomPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
        } else if (enterGameRoomPage != null && componentActivated.equals(enterGameRoomPage.getContinueToPlay())) {
            this.DealWithEnteringGameRoom();
        } else if (successfullyCreateGameRoomWaitingPage != null && componentActivated.equals(successfullyCreateGameRoomWaitingPage.getOpenPanel())) {
            this.DealWithCreatingGameRoom();
        } else if (successfullyCreateGameRoomWaitingPage != null && componentActivated.equals(successfullyCreateGameRoomWaitingPage.getContinuePanel())) {
            this.remove(successfullyCreateGameRoomWaitingPage);
            if (successfullyCreateGameRoomWaitingPage.GetWhetherServer()){
                serverRunnable = null;
                serverThread=null;
            }else {
                clientRunnable = null;
                clientThread = null;
            }
            LoadInGamePageForMultiUserWithTimeLimitation();
        }else if (diePageForMultiUser != null && componentActivated.equals(diePageForMultiUser.getBackOption())) {
            remove(diePageForMultiUser);
            diePageForMultiUser = null;
            this.LoadUserGameTypeChoosingPage();
            this.addMouseListener(this);
            this.setFocusable(true);
            repaint();
            this.setVisible(true);
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
        } else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && !((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.BLACK);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.WHITE, 6, false);
            ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        } else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && ((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.RED);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK, 6, false);
            ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        } else if (touristDiePage != null && componentActivated.equals(touristDiePage.getBackToMenuOption())) {
            touristDiePage.getBackToMenuOption().setBackground(Color.BLACK);
            touristDiePage.getBackToMenuOption().setVisible(true);
            touristDiePage.getBackToMenuOption().repaint();
        } else if (touristDiePage != null && componentActivated.equals(touristDiePage.getRestartOption())) {
            touristDiePage.getRestartOption().setBackground(Color.BLACK);
            touristDiePage.getRestartOption().setVisible(true);
            touristDiePage.getRestartOption().repaint();
        } else if (userLoginPage != null && componentActivated.equals(userLoginPage.GetLoginConfirmPanel())) {
            userLoginPage.GetLoginConfirmPanel().setBackground(new Color(0xDEAC80));
            userLoginPage.GetLoginConfirmPanel().setVisible(true);
            userLoginPage.GetLoginConfirmPanel().repaint();
        } else if (userRegistrationPage != null && userRegistrationPage.GetRegistrationConfirmPanel() != null && componentActivated.equals(userRegistrationPage.GetRegistrationConfirmPanel())) {
            userRegistrationPage.GetRegistrationConfirmPanel().setBackground(new Color(0xE8EFCF));
            userRegistrationPage.GetRegistrationConfirmPanel().setVisible(true);
            userRegistrationPage.GetRegistrationConfirmPanel().repaint();
        } else if (successfullyRegisteredPage != null && componentActivated.equals(successfullyRegisteredPage.GetBackToMenu())) {
            successfullyRegisteredPage.GetBackToMenu().setBackground(new Color(0xFDE49E));
            successfullyRegisteredPage.GetBackToMenu().setVisible(true);
            successfullyRegisteredPage.GetBackToMenu().repaint();
        } else if (successfullyRegisteredPage != null && componentActivated.equals(successfullyRegisteredPage.GetToLogin())) {
            successfullyRegisteredPage.GetToLogin().setBackground(new Color(0xFDE49E));
            successfullyRegisteredPage.GetToLogin().setVisible(true);
            successfullyRegisteredPage.GetToLogin().repaint();
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getThreeMinutesOption())) {
            timeLimitChoosingPage.getThreeMinutesOption().setBackground(Color.BLACK);
            timeLimitChoosingPage.getThreeMinutesOption().setVisible(true);
            timeLimitChoosingPage.getThreeMinutesOption().repaint();
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getSixMinutesOption())) {
            timeLimitChoosingPage.getSixMinutesOption().setBackground(Color.BLACK);
            timeLimitChoosingPage.getSixMinutesOption().setVisible(true);
            timeLimitChoosingPage.getSixMinutesOption().repaint();
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getDIYOption())) {
            timeLimitChoosingPage.getDIYOption().setBackground(Color.BLACK);
            timeLimitChoosingPage.getDIYOption().setVisible(true);
            timeLimitChoosingPage.getDIYOption().repaint();
        } else if (boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.GetContinueButton())) {
            boardSizeDIYPage.GetContinueButton().setBackground(Color.BLACK);
            boardSizeDIYPage.GetContinueLabel().setForeground(Color.WHITE);
        } else if ((inGamePageWithTimeLimit != null && !inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()) && componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.getButtonControllerSwitch().setBackground(Color.BLACK);
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.getButtonControllerSwitch().setBackground(Color.RED);
        } else if ((inGamePageWithoutTimeLimit != null && !inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()) && componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.getButtonControllerSwitch().setBackground(Color.BLACK);
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.getButtonControllerSwitch().setBackground(Color.RED);
        } else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getBackToMenuOption())) {
            touristWinningPage.getBackToMenuOption().setBackground(Color.BLACK);
            touristWinningPage.getBackToMenuOption().setVisible(true);
            touristWinningPage.getBackToMenuOption().repaint();
        } else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getContinueOption())) {
            touristWinningPage.getContinueOption().setBackground(Color.BLACK);
            touristWinningPage.getContinueOption().setVisible(true);
            touristWinningPage.getContinueOption().repaint();
        } else if (userWinningPage != null && componentActivated.equals(userWinningPage.getBackToMenuOption())) {
            userWinningPage.getBackToMenuOption().setBackground(Color.BLACK);
            userWinningPage.getBackToMenuOption().setVisible(true);
            userWinningPage.getBackToMenuOption().repaint();
        } else if (userWinningPage != null && componentActivated.equals(userWinningPage.getContinueOption())) {
            userWinningPage.getContinueOption().setBackground(Color.BLACK);
            userWinningPage.getContinueOption().setVisible(true);
            userWinningPage.getContinueOption().repaint();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetUpButton())) {
            inGamePageWithTimeLimit.GetUpButton().setBackground(new Color(0xFF7657));
            inGamePageWithTimeLimit.GetUpButton().setVisible(true);
            inGamePageWithTimeLimit.GetUpButton().repaint();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetDownButton())) {
            inGamePageWithTimeLimit.GetDownButton().setBackground(new Color(0xFF7657));
            inGamePageWithTimeLimit.GetDownButton().setVisible(true);
            inGamePageWithTimeLimit.GetDownButton().repaint();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetLeftButton())) {
            inGamePageWithTimeLimit.GetLeftButton().setBackground(new Color(0xFF7657));
            inGamePageWithTimeLimit.GetLeftButton().setVisible(true);
            inGamePageWithTimeLimit.GetLeftButton().repaint();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetRightButton())) {
            inGamePageWithTimeLimit.GetRightButton().setBackground(new Color(0xFF7657));
            inGamePageWithTimeLimit.GetRightButton().setVisible(true);
            inGamePageWithTimeLimit.GetRightButton().repaint();
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetUpButton())) {
            inGamePageWithoutTimeLimit.GetUpButton().setBackground(new Color(0xFF7657));
            inGamePageWithoutTimeLimit.GetUpButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetUpButton().repaint();
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetDownButton())) {
            inGamePageWithoutTimeLimit.GetDownButton().setBackground(new Color(0xFF7657));
            inGamePageWithoutTimeLimit.GetDownButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetDownButton().repaint();
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetLeftButton())) {
            inGamePageWithoutTimeLimit.GetLeftButton().setBackground(new Color(0xFF7657));
            inGamePageWithoutTimeLimit.GetLeftButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetLeftButton().repaint();
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetRightButton())) {
            inGamePageWithoutTimeLimit.GetRightButton().setBackground(new Color(0xFF7657));
            inGamePageWithoutTimeLimit.GetRightButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetRightButton().repaint();
        } else if ((boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.getSkinSwitcher())) && !skin) {
            boardSizeDIYPage.getSkinSwitcher().setBackground(Color.BLACK);
        } else if ((boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.getSkinSwitcher())) && skin) {
            boardSizeDIYPage.getSkinSwitcher().setBackground(Color.RED);
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getSinglePlayerOption())) {
            userGameTypeChoosingPage.getSinglePlayerOption().setBackground(Color.BLACK);
            userGameTypeChoosingPage.getSinglePlayerOption().setVisible(true);
            userGameTypeChoosingPage.getSinglePlayerOption().repaint();
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getMultiPlayerOption())) {
            userGameTypeChoosingPage.getMultiPlayerOption().setBackground(Color.BLACK);
            userGameTypeChoosingPage.getMultiPlayerOption().setVisible(true);
            userGameTypeChoosingPage.getMultiPlayerOption().repaint();
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getRecordOption())) {
            userGameTypeChoosingPage.getRecordOption().setBackground(Color.BLACK);
            userGameTypeChoosingPage.getRecordOption().setVisible(true);
            userGameTypeChoosingPage.getRecordOption().repaint();
        } else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getCompetitionOption())) {
            userSingleModeChoosingPage.getCompetitionOption().setBackground(Color.BLACK);
            userSingleModeChoosingPage.getCompetitionOption().setVisible(true);
            userSingleModeChoosingPage.getCompetitionOption().repaint();
        } else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getPracticeOption())) {
            userSingleModeChoosingPage.getPracticeOption().setBackground(Color.BLACK);
            userSingleModeChoosingPage.getPracticeOption().setVisible(true);
            userSingleModeChoosingPage.getPracticeOption().repaint();
        } else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithoutTimeLimitationOption())) {
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().setBackground(Color.BLACK);
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().setVisible(true);
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().repaint();
        } else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithTimeLimitationOption())) {
            userCompetitionChoosingPage.getWithTimeLimitationOption().setBackground(Color.BLACK);
            userCompetitionChoosingPage.getWithTimeLimitationOption().setVisible(true);
            userCompetitionChoosingPage.getWithTimeLimitationOption().repaint();
        } else if (userPracticeModeChoosingPage != null && componentActivated.equals(userPracticeModeChoosingPage.getWithoutTimeLimitationOption())) {
            userPracticeModeChoosingPage.getWithoutTimeLimitationOption().setBackground(Color.BLACK);
            userPracticeModeChoosingPage.getWithoutTimeLimitationOption().setVisible(true);
            userPracticeModeChoosingPage.getWithoutTimeLimitationOption().repaint();
        } else if (userPracticeModeChoosingPage != null && componentActivated.equals(userPracticeModeChoosingPage.getWithTimeLimitationOption())) {
            userPracticeModeChoosingPage.getWithTimeLimitationOption().setBackground(Color.BLACK);
            userPracticeModeChoosingPage.getWithTimeLimitationOption().setVisible(true);
            userPracticeModeChoosingPage.getWithTimeLimitationOption().repaint();
        } else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getBackToMenuOption())) {
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().setBackground(Color.BLACK);
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().setVisible(true);
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().repaint();
        } else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getRestartOption())) {
            userCompetitionWithoutLimitDiePage.getRestartOption().setBackground(Color.BLACK);
            userCompetitionWithoutLimitDiePage.getRestartOption().setVisible(true);
            userCompetitionWithoutLimitDiePage.getRestartOption().repaint();
        } else if (userPracticeWithoutTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption())) {
            userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption().setBackground(Color.BLACK);
            userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption().setVisible(true);
            userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption().repaint();
        } else if (userPracticeWithoutTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption())) {
            userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption().setBackground(Color.BLACK);
            userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption().setVisible(true);
            userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption().repaint();
        } else if (userPracticeWithTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption())) {
            userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption().setBackground(Color.BLACK);
            userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption().setVisible(true);
            userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption().repaint();
        } else if (userPracticeWithTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption())) {
            userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption().setBackground(Color.BLACK);
            userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption().setVisible(true);
            userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption().repaint();
        } else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getBackToMenuOption())) {
            userCompetitionWithLimitDiePage.getBackToMenuOption().setBackground(Color.BLACK);
            userCompetitionWithLimitDiePage.getBackToMenuOption().setVisible(true);
            userCompetitionWithLimitDiePage.getBackToMenuOption().repaint();
        } else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getRestartOption())) {
            userCompetitionWithLimitDiePage.getRestartOption().setBackground(Color.BLACK);
            userCompetitionWithLimitDiePage.getRestartOption().setVisible(true);
            userCompetitionWithLimitDiePage.getRestartOption().repaint();
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getThreeOption())) {
            userPracticeWithoutLimitationModeChoosingPage.getThreeOption().setBackground(Color.BLACK);
            userPracticeWithoutLimitationModeChoosingPage.getThreeOption().setVisible(true);
            userPracticeWithoutLimitationModeChoosingPage.getThreeOption().repaint();
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getFourOption())) {
            userPracticeWithoutLimitationModeChoosingPage.getFourOption().setBackground(Color.BLACK);
            userPracticeWithoutLimitationModeChoosingPage.getFourOption().setVisible(true);
            userPracticeWithoutLimitationModeChoosingPage.getFourOption().repaint();
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getDIYOption())) {
            userPracticeWithoutLimitationModeChoosingPage.getDIYOption().setBackground(Color.BLACK);
            userPracticeWithoutLimitationModeChoosingPage.getDIYOption().setVisible(true);
            userPracticeWithoutLimitationModeChoosingPage.getDIYOption().repaint();
        } else if (userPracticeWinningPage != null && componentActivated.equals(userPracticeWinningPage.getContinueOption())) {
            userPracticeWinningPage.getContinueOption().setBackground(Color.BLACK);
            userPracticeWinningPage.getContinueOption().setVisible(true);
            userPracticeWinningPage.getContinueOption().repaint();
        } else if (userPracticeWinningPage != null && componentActivated.equals(userPracticeWinningPage.getBackToMenuOption())) {
            userPracticeWinningPage.getBackToMenuOption().setBackground(Color.BLACK);
            userPracticeWinningPage.getBackToMenuOption().setVisible(true);
            userPracticeWinningPage.getBackToMenuOption().repaint();
        } else if (userPracticeWithoutLimitDiePage != null && componentActivated.equals(userPracticeWithoutLimitDiePage.getRestartOption())) {
            userPracticeWithoutLimitDiePage.getRestartOption().setBackground(Color.BLACK);
            userPracticeWithoutLimitDiePage.getRestartOption().setVisible(true);
            userPracticeWithoutLimitDiePage.getRestartOption().repaint();
        } else if (userPracticeWithoutLimitDiePage != null && componentActivated.equals(userPracticeWithoutLimitDiePage.getBackToMenuOption())) {
            userPracticeWithoutLimitDiePage.getBackToMenuOption().setBackground(Color.BLACK);
            userPracticeWithoutLimitDiePage.getBackToMenuOption().setVisible(true);
            userPracticeWithoutLimitDiePage.getBackToMenuOption().repaint();
        } else if (askingForArchivePanel != null && componentActivated.equals(askingForArchivePanel.getContinueToPlay())) {
            askingForArchivePanel.getContinueToPlay().setBackground(Color.BLACK);
            askingForArchivePanel.getContinueToPlay().setVisible(true);
            askingForArchivePanel.getContinueToPlay().repaint();
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption())) {
            userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption().setBackground(Color.BLACK);
            userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption().setVisible(true);
            userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption().repaint();
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getSixMinutesOption())) {
            userPracticeWithLimitationModeChoosingPage.getSixMinutesOption().setBackground(Color.BLACK);
            userPracticeWithLimitationModeChoosingPage.getSixMinutesOption().setVisible(true);
            userPracticeWithLimitationModeChoosingPage.getSixMinutesOption().repaint();
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getDIYOption())) {
            userPracticeWithLimitationModeChoosingPage.getDIYOption().setBackground(Color.BLACK);
            userPracticeWithLimitationModeChoosingPage.getDIYOption().setVisible(true);
            userPracticeWithLimitationModeChoosingPage.getDIYOption().repaint();
        } else if (userPracticeWithLimitDiePage != null && componentActivated.equals(userPracticeWithLimitDiePage.getRestartOption())) {
            userPracticeWithLimitDiePage.getRestartOption().setBackground(Color.BLACK);
            userPracticeWithLimitDiePage.getRestartOption().setVisible(true);
            userPracticeWithLimitDiePage.getRestartOption().repaint();
        } else if (userPracticeWithLimitDiePage != null && componentActivated.equals(userPracticeWithLimitDiePage.getBackToMenuOption())) {
            userPracticeWithLimitDiePage.getBackToMenuOption().setBackground(Color.BLACK);
            userPracticeWithLimitDiePage.getBackToMenuOption().setVisible(true);
            userPracticeWithLimitDiePage.getBackToMenuOption().repaint();
        } else if (recordModeSelectionPage != null && componentActivated.equals(recordModeSelectionPage.getWithTimeLimitationOption())) {
            recordModeSelectionPage.getWithTimeLimitationOption().setBackground(Color.BLACK);
            recordModeSelectionPage.getWithTimeLimitationOption().setVisible(true);
            recordModeSelectionPage.getWithTimeLimitationOption().repaint();
        } else if (recordModeSelectionPage != null && componentActivated.equals(recordModeSelectionPage.getWithoutTimeLimitationOption())) {
            recordModeSelectionPage.getWithoutTimeLimitationOption().setBackground(Color.BLACK);
            recordModeSelectionPage.getWithoutTimeLimitationOption().setVisible(true);
            recordModeSelectionPage.getWithoutTimeLimitationOption().repaint();
        } else if (whetherNewGameRoomPage != null && componentActivated.equals(whetherNewGameRoomPage.getCreateNewGameRoomOption())) {
            whetherNewGameRoomPage.getCreateNewGameRoomOption().setBackground(Color.BLACK);
            whetherNewGameRoomPage.getCreateNewGameRoomOption().setVisible(true);
            whetherNewGameRoomPage.getCreateNewGameRoomOption().repaint();
        } else if (whetherNewGameRoomPage != null && componentActivated.equals(whetherNewGameRoomPage.getEnterExistingGameRoomOption())) {
            whetherNewGameRoomPage.getEnterExistingGameRoomOption().setBackground(Color.BLACK);
            whetherNewGameRoomPage.getEnterExistingGameRoomOption().setVisible(true);
            whetherNewGameRoomPage.getEnterExistingGameRoomOption().repaint();
        } else if (createGameRoomPage != null && componentActivated.equals(createGameRoomPage.getContinueToPlay())) {
            createGameRoomPage.getContinueToPlay().setBackground(Color.BLACK);
            createGameRoomPage.getContinueToPlay().setVisible(true);
            createGameRoomPage.getContinueToPlay().repaint();
        } else if (enterGameRoomPage != null && componentActivated.equals(enterGameRoomPage.getContinueToPlay())) {
            enterGameRoomPage.getContinueToPlay().setBackground(Color.BLACK);
            enterGameRoomPage.getContinueToPlay().setVisible(true);
            enterGameRoomPage.getContinueToPlay().repaint();
        } else if (successfullyCreateGameRoomWaitingPage != null && componentActivated.equals(successfullyCreateGameRoomWaitingPage.getContinuePanel())) {
            successfullyCreateGameRoomWaitingPage.getContinuePanel().setBackground(Color.BLACK);
            successfullyCreateGameRoomWaitingPage.getContinuePanel().setVisible(true);
            successfullyCreateGameRoomWaitingPage.getContinuePanel().repaint();
        } else if (successfullyCreateGameRoomWaitingPage != null && componentActivated.equals(successfullyCreateGameRoomWaitingPage.getOpenPanel())) {
            successfullyCreateGameRoomWaitingPage.getOpenPanel().setBackground(Color.BLACK);
            successfullyCreateGameRoomWaitingPage.getOpenPanel().setVisible(true);
            successfullyCreateGameRoomWaitingPage.getOpenPanel().repaint();
        }else if (diePageForMultiUser != null && componentActivated.equals(diePageForMultiUser.getBackOption())) {
            diePageForMultiUser.getBackOption().setBackground(Color.BLACK);
            diePageForMultiUser.getBackOption().setVisible(true);
            diePageForMultiUser.getBackOption().repaint();
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
        } else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && !((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.WHITE);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK, 6, false);
            ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        } else if (boardSizeDIYPage != null && whetherTheComponentIsBelongingToTheBlocks(componentActivated) && ((UnitBlockInDIY) componentActivated).getWhetherChoosing()) {
            componentActivated.setBackground(Color.LIGHT_GRAY);
            Border borderOfTheBlock = BorderFactory.createLineBorder(Color.WHITE, 6, false);
            ((UnitBlockInDIY) componentActivated).setBorder(borderOfTheBlock);
            componentActivated.setVisible(true);
            componentActivated.repaint();
        } else if (touristDiePage != null && componentActivated.equals(touristDiePage.getBackToMenuOption())) {
            touristDiePage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            touristDiePage.getBackToMenuOption().setVisible(true);
            touristDiePage.getBackToMenuOption().repaint();
        } else if (touristDiePage != null && componentActivated.equals(touristDiePage.getRestartOption())) {
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
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getThreeMinutesOption())) {
            timeLimitChoosingPage.getThreeMinutesOption().setBackground(Color.LIGHT_GRAY);
            timeLimitChoosingPage.getThreeMinutesOption().setVisible(true);
            timeLimitChoosingPage.getThreeMinutesOption().repaint();
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getSixMinutesOption())) {
            timeLimitChoosingPage.getSixMinutesOption().setBackground(Color.LIGHT_GRAY);
            timeLimitChoosingPage.getSixMinutesOption().setVisible(true);
            timeLimitChoosingPage.getSixMinutesOption().repaint();
        } else if (timeLimitChoosingPage != null && componentActivated.equals(timeLimitChoosingPage.getDIYOption())) {
            timeLimitChoosingPage.getDIYOption().setBackground(Color.LIGHT_GRAY);
            timeLimitChoosingPage.getDIYOption().setVisible(true);
            timeLimitChoosingPage.getDIYOption().repaint();
        } else if (boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.GetContinueButton())) {
            boardSizeDIYPage.GetContinueButton().setBackground(Color.LIGHT_GRAY);
            boardSizeDIYPage.GetContinueLabel().setForeground(Color.BLACK);
        } else if (inGamePageWithTimeLimit != null && (!inGamePageWithTimeLimit.GetWhetherDirectionButtonOut()) && componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.getButtonControllerSwitch().setBackground(null);
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithTimeLimit.getButtonControllerSwitch().setBackground(null);
        } else if (inGamePageWithoutTimeLimit != null && (!inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut()) && componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.getButtonControllerSwitch().setBackground(null);
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.getButtonControllerSwitch())) {
            inGamePageWithoutTimeLimit.getButtonControllerSwitch().setBackground(null);
        } else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getBackToMenuOption())) {
            touristWinningPage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            touristWinningPage.getBackToMenuOption().setVisible(true);
            touristWinningPage.getBackToMenuOption().repaint();
        } else if (touristWinningPage != null && componentActivated.equals(touristWinningPage.getContinueOption())) {
            touristWinningPage.getContinueOption().setBackground(Color.LIGHT_GRAY);
            touristWinningPage.getContinueOption().setVisible(true);
            touristWinningPage.getContinueOption().repaint();
        } else if (userWinningPage != null && componentActivated.equals(userWinningPage.getBackToMenuOption())) {
            userWinningPage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userWinningPage.getBackToMenuOption().setVisible(true);
            userWinningPage.getBackToMenuOption().repaint();
        } else if (userWinningPage != null && componentActivated.equals(userWinningPage.getContinueOption())) {
            userWinningPage.getContinueOption().setBackground(Color.LIGHT_GRAY);
            userWinningPage.getContinueOption().setVisible(true);
            userWinningPage.getContinueOption().repaint();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetUpButton())) {
            inGamePageWithTimeLimit.GetUpButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithTimeLimit.GetUpButton().setVisible(true);
            inGamePageWithTimeLimit.GetUpButton().repaint();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetDownButton())) {
            inGamePageWithTimeLimit.GetDownButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithTimeLimit.GetDownButton().setVisible(true);
            inGamePageWithTimeLimit.GetDownButton().repaint();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetLeftButton())) {
            inGamePageWithTimeLimit.GetLeftButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithTimeLimit.GetLeftButton().setVisible(true);
            inGamePageWithTimeLimit.GetLeftButton().repaint();
        } else if (inGamePageWithTimeLimit != null && inGamePageWithTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithTimeLimit.GetRightButton())) {
            inGamePageWithTimeLimit.GetRightButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithTimeLimit.GetRightButton().setVisible(true);
            inGamePageWithTimeLimit.GetRightButton().repaint();
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetUpButton())) {
            inGamePageWithoutTimeLimit.GetUpButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithoutTimeLimit.GetUpButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetUpButton().repaint();
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetDownButton())) {
            inGamePageWithoutTimeLimit.GetDownButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithoutTimeLimit.GetDownButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetDownButton().repaint();
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetLeftButton())) {
            inGamePageWithoutTimeLimit.GetLeftButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithoutTimeLimit.GetLeftButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetLeftButton().repaint();
        } else if (inGamePageWithoutTimeLimit != null && inGamePageWithoutTimeLimit.GetWhetherDirectionButtonOut() && componentActivated.equals(inGamePageWithoutTimeLimit.GetRightButton())) {
            inGamePageWithoutTimeLimit.GetRightButton().setBackground(new Color(0xFEFEA4));
            inGamePageWithoutTimeLimit.GetRightButton().setVisible(true);
            inGamePageWithoutTimeLimit.GetRightButton().repaint();
        } else if ((boardSizeDIYPage != null && componentActivated.equals(boardSizeDIYPage.getSkinSwitcher()))) {
            boardSizeDIYPage.getSkinSwitcher().setBackground(null);
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getSinglePlayerOption())) {
            userGameTypeChoosingPage.getSinglePlayerOption().setBackground(Color.LIGHT_GRAY);
            userGameTypeChoosingPage.getSinglePlayerOption().setVisible(true);
            userGameTypeChoosingPage.getSinglePlayerOption().repaint();
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getMultiPlayerOption())) {
            userGameTypeChoosingPage.getMultiPlayerOption().setBackground(Color.LIGHT_GRAY);
            userGameTypeChoosingPage.getMultiPlayerOption().setVisible(true);
            userGameTypeChoosingPage.getMultiPlayerOption().repaint();
        } else if (userGameTypeChoosingPage != null && componentActivated.equals(userGameTypeChoosingPage.getRecordOption())) {
            userGameTypeChoosingPage.getRecordOption().setBackground(Color.LIGHT_GRAY);
            userGameTypeChoosingPage.getRecordOption().setVisible(true);
            userGameTypeChoosingPage.getRecordOption().repaint();
        } else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getCompetitionOption())) {
            userSingleModeChoosingPage.getCompetitionOption().setBackground(Color.LIGHT_GRAY);
            userSingleModeChoosingPage.getCompetitionOption().setVisible(true);
            userSingleModeChoosingPage.getCompetitionOption().repaint();
        } else if (userSingleModeChoosingPage != null && componentActivated.equals(userSingleModeChoosingPage.getPracticeOption())) {
            userSingleModeChoosingPage.getPracticeOption().setBackground(Color.LIGHT_GRAY);
            userSingleModeChoosingPage.getPracticeOption().setVisible(true);
            userSingleModeChoosingPage.getPracticeOption().repaint();
        } else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithoutTimeLimitationOption())) {
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().setVisible(true);
            userCompetitionChoosingPage.getWithoutTimeLimitationOption().repaint();
        } else if (userCompetitionChoosingPage != null && componentActivated.equals(userCompetitionChoosingPage.getWithTimeLimitationOption())) {
            userCompetitionChoosingPage.getWithTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionChoosingPage.getWithTimeLimitationOption().setVisible(true);
            userCompetitionChoosingPage.getWithTimeLimitationOption().repaint();
        } else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getBackToMenuOption())) {
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().setVisible(true);
            userCompetitionWithoutLimitDiePage.getBackToMenuOption().repaint();
        } else if (userCompetitionWithoutLimitDiePage != null && componentActivated.equals(userCompetitionWithoutLimitDiePage.getRestartOption())) {
            userCompetitionWithoutLimitDiePage.getRestartOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionWithoutLimitDiePage.getRestartOption().setVisible(true);
            userCompetitionWithoutLimitDiePage.getRestartOption().repaint();
        } else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getBackToMenuOption())) {
            userCompetitionWithLimitDiePage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionWithLimitDiePage.getBackToMenuOption().setVisible(true);
            userCompetitionWithLimitDiePage.getBackToMenuOption().repaint();
        } else if (userCompetitionWithLimitDiePage != null && componentActivated.equals(userCompetitionWithLimitDiePage.getRestartOption())) {
            userCompetitionWithLimitDiePage.getRestartOption().setBackground(Color.LIGHT_GRAY);
            userCompetitionWithLimitDiePage.getRestartOption().setVisible(true);
            userCompetitionWithLimitDiePage.getRestartOption().repaint();
        } else if (userPracticeModeChoosingPage != null && componentActivated.equals(userPracticeModeChoosingPage.getWithoutTimeLimitationOption())) {
            userPracticeModeChoosingPage.getWithoutTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            userPracticeModeChoosingPage.getWithoutTimeLimitationOption().setVisible(true);
            userPracticeModeChoosingPage.getWithoutTimeLimitationOption().repaint();
        } else if (userPracticeModeChoosingPage != null && componentActivated.equals(userPracticeModeChoosingPage.getWithTimeLimitationOption())) {
            userPracticeModeChoosingPage.getWithTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            userPracticeModeChoosingPage.getWithTimeLimitationOption().setVisible(true);
            userPracticeModeChoosingPage.getWithTimeLimitationOption().repaint();
        } else if (userPracticeWithoutTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption())) {
            userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption().setVisible(true);
            userPracticeWithoutTimeLimitModeWhetherNewPage.getNewGameOption().repaint();
        } else if (userPracticeWithoutTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption())) {
            userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption().setVisible(true);
            userPracticeWithoutTimeLimitModeWhetherNewPage.getExistingArchiveOption().repaint();
        } else if (userPracticeWithTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption())) {
            userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption().setVisible(true);
            userPracticeWithTimeLimitModeWhetherNewPage.getNewGameOption().repaint();
        } else if (userPracticeWithTimeLimitModeWhetherNewPage != null && componentActivated.equals(userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption())) {
            userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption().setVisible(true);
            userPracticeWithTimeLimitModeWhetherNewPage.getExistingArchiveOption().repaint();
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getThreeOption())) {
            userPracticeWithoutLimitationModeChoosingPage.getThreeOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithoutLimitationModeChoosingPage.getThreeOption().setVisible(true);
            userPracticeWithoutLimitationModeChoosingPage.getThreeOption().repaint();
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getFourOption())) {
            userPracticeWithoutLimitationModeChoosingPage.getFourOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithoutLimitationModeChoosingPage.getFourOption().setVisible(true);
            userPracticeWithoutLimitationModeChoosingPage.getFourOption().repaint();
        } else if (userPracticeWithoutLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithoutLimitationModeChoosingPage.getDIYOption())) {
            userPracticeWithoutLimitationModeChoosingPage.getDIYOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithoutLimitationModeChoosingPage.getDIYOption().setVisible(true);
            userPracticeWithoutLimitationModeChoosingPage.getDIYOption().repaint();
        } else if (userPracticeWinningPage != null && componentActivated.equals(userPracticeWinningPage.getContinueOption())) {
            userPracticeWinningPage.getContinueOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWinningPage.getContinueOption().setVisible(true);
            userPracticeWinningPage.getContinueOption().repaint();
        } else if (userPracticeWinningPage != null && componentActivated.equals(userPracticeWinningPage.getBackToMenuOption())) {
            userPracticeWinningPage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWinningPage.getBackToMenuOption().setVisible(true);
            userPracticeWinningPage.getBackToMenuOption().repaint();
        } else if (userPracticeWithoutLimitDiePage != null && componentActivated.equals(userPracticeWithoutLimitDiePage.getRestartOption())) {
            userPracticeWithoutLimitDiePage.getRestartOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithoutLimitDiePage.getRestartOption().setVisible(true);
            userPracticeWithoutLimitDiePage.getRestartOption().repaint();
        } else if (userPracticeWithoutLimitDiePage != null && componentActivated.equals(userPracticeWithoutLimitDiePage.getBackToMenuOption())) {
            userPracticeWithoutLimitDiePage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithoutLimitDiePage.getBackToMenuOption().setVisible(true);
            userPracticeWithoutLimitDiePage.getBackToMenuOption().repaint();
        } else if (askingForArchivePanel != null && componentActivated.equals(askingForArchivePanel.getContinueToPlay())) {
            askingForArchivePanel.getContinueToPlay().setBackground(Color.LIGHT_GRAY);
            askingForArchivePanel.getContinueToPlay().setVisible(true);
            askingForArchivePanel.getContinueToPlay().repaint();
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption())) {
            userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption().setVisible(true);
            userPracticeWithLimitationModeChoosingPage.getThreeMinutesOption().repaint();
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getSixMinutesOption())) {
            userPracticeWithLimitationModeChoosingPage.getSixMinutesOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithLimitationModeChoosingPage.getSixMinutesOption().setVisible(true);
            userPracticeWithLimitationModeChoosingPage.getSixMinutesOption().repaint();
        } else if (userPracticeWithLimitationModeChoosingPage != null && componentActivated.equals(userPracticeWithLimitationModeChoosingPage.getDIYOption())) {
            userPracticeWithLimitationModeChoosingPage.getDIYOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithLimitationModeChoosingPage.getDIYOption().setVisible(true);
            userPracticeWithLimitationModeChoosingPage.getDIYOption().repaint();
        } else if (userPracticeWithLimitDiePage != null && componentActivated.equals(userPracticeWithLimitDiePage.getRestartOption())) {
            userPracticeWithLimitDiePage.getRestartOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithLimitDiePage.getRestartOption().setVisible(true);
            userPracticeWithLimitDiePage.getRestartOption().repaint();
        } else if (userPracticeWithLimitDiePage != null && componentActivated.equals(userPracticeWithLimitDiePage.getBackToMenuOption())) {
            userPracticeWithLimitDiePage.getBackToMenuOption().setBackground(Color.LIGHT_GRAY);
            userPracticeWithLimitDiePage.getBackToMenuOption().setVisible(true);
            userPracticeWithLimitDiePage.getBackToMenuOption().repaint();
        } else if (recordModeSelectionPage != null && componentActivated.equals(recordModeSelectionPage.getWithTimeLimitationOption())) {
            recordModeSelectionPage.getWithTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            recordModeSelectionPage.getWithTimeLimitationOption().setVisible(true);
            recordModeSelectionPage.getWithTimeLimitationOption().repaint();
        } else if (recordModeSelectionPage != null && componentActivated.equals(recordModeSelectionPage.getWithoutTimeLimitationOption())) {
            recordModeSelectionPage.getWithoutTimeLimitationOption().setBackground(Color.LIGHT_GRAY);
            recordModeSelectionPage.getWithoutTimeLimitationOption().setVisible(true);
            recordModeSelectionPage.getWithoutTimeLimitationOption().repaint();
        } else if (whetherNewGameRoomPage != null && componentActivated.equals(whetherNewGameRoomPage.getCreateNewGameRoomOption())) {
            whetherNewGameRoomPage.getCreateNewGameRoomOption().setBackground(Color.LIGHT_GRAY);
            whetherNewGameRoomPage.getCreateNewGameRoomOption().setVisible(true);
            whetherNewGameRoomPage.getCreateNewGameRoomOption().repaint();
        } else if (whetherNewGameRoomPage != null && componentActivated.equals(whetherNewGameRoomPage.getEnterExistingGameRoomOption())) {
            whetherNewGameRoomPage.getEnterExistingGameRoomOption().setBackground(Color.LIGHT_GRAY);
            whetherNewGameRoomPage.getEnterExistingGameRoomOption().setVisible(true);
            whetherNewGameRoomPage.getEnterExistingGameRoomOption().repaint();
        } else if (createGameRoomPage != null && componentActivated.equals(createGameRoomPage.getContinueToPlay())) {
            createGameRoomPage.getContinueToPlay().setBackground(Color.LIGHT_GRAY);
            createGameRoomPage.getContinueToPlay().setVisible(true);
            createGameRoomPage.getContinueToPlay().repaint();
        } else if (enterGameRoomPage != null && componentActivated.equals(enterGameRoomPage.getContinueToPlay())) {
            enterGameRoomPage.getContinueToPlay().setBackground(Color.LIGHT_GRAY);
            enterGameRoomPage.getContinueToPlay().setVisible(true);
            enterGameRoomPage.getContinueToPlay().repaint();
        } else if (successfullyCreateGameRoomWaitingPage != null && componentActivated.equals(successfullyCreateGameRoomWaitingPage.getContinuePanel())) {
            successfullyCreateGameRoomWaitingPage.getContinuePanel().setBackground(Color.LIGHT_GRAY);
            successfullyCreateGameRoomWaitingPage.getContinuePanel().setVisible(true);
            successfullyCreateGameRoomWaitingPage.getContinuePanel().repaint();
        } else if (successfullyCreateGameRoomWaitingPage != null && componentActivated.equals(successfullyCreateGameRoomWaitingPage.getOpenPanel())) {
            successfullyCreateGameRoomWaitingPage.getOpenPanel().setBackground(Color.LIGHT_GRAY);
            successfullyCreateGameRoomWaitingPage.getOpenPanel().setVisible(true);
            successfullyCreateGameRoomWaitingPage.getOpenPanel().repaint();
        }else if (diePageForMultiUser != null && componentActivated.equals(diePageForMultiUser.getBackOption())) {
            diePageForMultiUser.getBackOption().setBackground(Color.LIGHT_GRAY);
            diePageForMultiUser.getBackOption().setVisible(true);
            diePageForMultiUser.getBackOption().repaint();
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
        if (!currentInformationAboutCoordinate.isEmpty()) {
            currentInformationAboutCoordinate = getMinimumXCoordinates(currentInformationAboutCoordinate);
            currentInformationAboutCoordinate = getMinimumYCoordinates(currentInformationAboutCoordinate);
            controllingCenter.setInformationOfAllTheCoordinateOfTheBoardUnit(currentInformationAboutCoordinate);
            controllingCenter.SetUpTheControllingCenterForDIY();
        }
    }

    private void UpdateTheCoordinateSetInTheControllingCenterForFour() {
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

    private void UpdateTheCoordinateSetInTheControllingCenterForThree() {
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
            if (whetherContinue) {
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
                currentCoordinateInformation.set(indexInPoints * 2 + 1, currentCoordinateInformation.get(indexInPoints * 2 + 1) - 1);
                if (currentCoordinateInformation.get(indexInPoints * 2 + 1) < 0) {
                    whetherContinue = false;
                    break;
                }
            }
            if (whetherContinue) {
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
                    } else if (controllingCenter.GetInCompetition()) {
                        remove(inGamePageWithoutTimeLimit);
                        inGamePageWithoutTimeLimit = null;
                        LoadUserCompetitionWithoutLimitDiePage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    } else if (!controllingCenter.GetInCompetition()) {
                        remove(inGamePageWithoutTimeLimit);
                        inGamePageWithoutTimeLimit = null;
                        LoadUserPracticeWithoutLimitDiePage();
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
        if (controllingCenter.GetWhetherReachedTheTargetScore() && !controllingCenter.GetWhetherAlreadyShownWinningPage()) {
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
                    } else if (controllingCenter.GetInCompetition()) {
                        remove(inGamePageWithoutTimeLimit);
                        LoadUserWinningPage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    } else if (!controllingCenter.GetInCompetition()) {
                        remove(inGamePageWithoutTimeLimit);
                        LoadUserPracticeWinningPage();
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
                    } else if (controllingCenter.GetInCompetition()) {
                        remove(inGamePageWithTimeLimit);
                        LoadUserCompetitionWithLimitDiePage();
                        setFocusable(true);
                        repaint();
                        setVisible(true);
                    } else if (!controllingCenter.GetInCompetition()) {
                        remove(inGamePageWithTimeLimit);
                        LoadUserPracticeWithLimitDiePage();
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

    public void JudgeWhetherEndOfGameWithTimeLimitForMultiUser() {
        if (!controllingCenter.getGameValidity()) {
            timerIsRunning = true;
            Timer timer = new Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timerIsRunning = false;
                    if (inGamePageWithTimeLimitForMultiUser.isWhetherServer()) {
                        server = new Server(controllingCenter.getCurrentGameScore(),user,TotalGameFrame.this);
                        if (enemyScore> controllingCenter.getCurrentGameScore()){
                            TotalGameFrame.this.remove(inGamePageWithTimeLimitForMultiUser);
                            inGamePageWithTimeLimitForMultiUser = null;
                            server = null;
                            TotalGameFrame.this.LoadDiePageForMultiUser(String.format("%d : %d, You Lose!",controllingCenter.getCurrentGameScore(),enemyScore));
                            controllingCenter = null;
                            TotalGameFrame.this.setVisible(false);
                            TotalGameFrame.this.repaint();
                            TotalGameFrame.this.setVisible(true);
                        } else if (enemyScore< controllingCenter.getCurrentGameScore()){
                            TotalGameFrame.this.remove(inGamePageWithTimeLimitForMultiUser);
                            inGamePageWithTimeLimitForMultiUser = null;
                            server = null;
                            TotalGameFrame.this.LoadDiePageForMultiUser(String.format("%d : %d, You Win!",controllingCenter.getCurrentGameScore(),enemyScore));
                            controllingCenter = null;
                            TotalGameFrame.this.setVisible(false);
                            TotalGameFrame.this.repaint();
                            TotalGameFrame.this.setVisible(true);
                        } else {
                            TotalGameFrame.this.remove(inGamePageWithTimeLimitForMultiUser);
                            inGamePageWithTimeLimitForMultiUser = null;
                            server = null;
                            TotalGameFrame.this.LoadDiePageForMultiUser(String.format("%d : %d, The Same!",controllingCenter.getCurrentGameScore(),enemyScore));
                            controllingCenter = null;
                            TotalGameFrame.this.setVisible(false);
                            TotalGameFrame.this.repaint();
                            TotalGameFrame.this.setVisible(true);
                        }
                    } else {
                        try {
                            client = new Client(controllingCenter.getCurrentGameScore(),IPOfServer,user,TotalGameFrame.this);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        if (enemyScore> controllingCenter.getCurrentGameScore()){
                            TotalGameFrame.this.remove(inGamePageWithTimeLimitForMultiUser);
                            inGamePageWithTimeLimitForMultiUser = null;
                            client = null;
                            TotalGameFrame.this.LoadDiePageForMultiUser(String.format("%d : %d, You Lose!",controllingCenter.getCurrentGameScore(),enemyScore));
                            controllingCenter = null;
                            TotalGameFrame.this.setVisible(false);
                            TotalGameFrame.this.repaint();
                            TotalGameFrame.this.setVisible(true);
                        } else if (enemyScore< controllingCenter.getCurrentGameScore()){
                            TotalGameFrame.this.remove(inGamePageWithTimeLimitForMultiUser);
                            inGamePageWithTimeLimitForMultiUser = null;
                            client = null;
                            TotalGameFrame.this.LoadDiePageForMultiUser(String.format("%d : %d, You Win!",controllingCenter.getCurrentGameScore(),enemyScore));
                            controllingCenter = null;
                            TotalGameFrame.this.setVisible(false);
                            TotalGameFrame.this.repaint();
                            TotalGameFrame.this.setVisible(true);
                        } else {
                            TotalGameFrame.this.remove(inGamePageWithTimeLimitForMultiUser);
                            inGamePageWithTimeLimitForMultiUser = null;
                            client = null;
                            TotalGameFrame.this.LoadDiePageForMultiUser(String.format("%d : %d, The Same!",controllingCenter.getCurrentGameScore(),enemyScore));
                            controllingCenter = null;
                            TotalGameFrame.this.setVisible(false);
                            TotalGameFrame.this.repaint();
                            TotalGameFrame.this.setVisible(true);
                        }
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public Void WaitToShowTheDiePage() {
        while (!whetherMultiGameOver) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.remove(inGamePageWithTimeLimitForMultiUser);
            if (whetherMultiGameOver) {
                if (inGamePageWithTimeLimitForMultiUser.isWhetherServer()) {
                    if (serverRunnable.getServer().whetherSame) {
                        LoadDiePageForMultiUser("The Same!");
                    } else if (serverRunnable.getServer().whetherWon) {
                        LoadDiePageForMultiUser("You won!");
                    } else if (serverRunnable.getServer().whetherWon) {
                        LoadDiePageForMultiUser("You lose!");
                    }
                } else {
                    if (clientRunnable.getClient().whetherSame) {
                        LoadDiePageForMultiUser("The Same!");
                    } else if (clientRunnable.getClient().whetherWon) {
                        LoadDiePageForMultiUser("You won!");
                    } else if (clientRunnable.getClient().whetherWon) {
                        LoadDiePageForMultiUser("You lose!");
                    }
                }
            }
        }
        return null;
    }

    private void DealWithRegistrationIssue() {
        boolean whetherRegisterSuccessfully = true;
        UserManger userManger = new UserManger();
        userRegistrationPage.GetRegistrationFunctionPanel().CleanExistingWarning();
        if (!userManger.ExamineValidityOfUserName(userRegistrationPage.GetUserName())) {
            userRegistrationPage.GetRegistrationFunctionPanel().EstablishWarnForUserName(userManger.getFeedBackForUserName());
            whetherRegisterSuccessfully = false;
        }
        if (!userManger.ExamineValidityOfPassWords(userRegistrationPage.GetUserPassWord())) {
            userRegistrationPage.GetRegistrationFunctionPanel().EstablishWarnForPassWords(userManger.getFeedBackForPassWords());
            whetherRegisterSuccessfully = false;
        }
        if (!userManger.ExamineValidityOfAgainPasswords(userRegistrationPage.GetUserAgainPassWord(), userRegistrationPage.GetUserPassWord())) {
            userRegistrationPage.GetRegistrationFunctionPanel().EstablishWarnForAgainPassWords(userManger.getFeedbackForConfirmPasswords());
            whetherRegisterSuccessfully = false;
        }
        if (whetherRegisterSuccessfully) {
            userManger.CreateDirectoryForSpecificUser(userRegistrationPage.GetUserName());
            documentReaderAndWriter = new DocumentReaderAndWriter(controllingCenter, userRegistrationPage.GetUserName());
            documentReaderAndWriter.saveUserLoginInformation(userRegistrationPage.GetUserName(), userRegistrationPage.GetUserPassWord());
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

    private void DealWithLoginIssue() {
        boolean whetherLoginSuccessfully = true;
        UserManger userManger = new UserManger();
        userLoginPage.getLoginFunctionPanel().CleanExistingWarning();
        if (!userManger.ExamineValidityOfUserNameWhenLogin(userLoginPage.GetUserName())) {
            userLoginPage.getLoginFunctionPanel().EstablishWarnForUserName(userManger.getFeedBackForUserNameInLogin());
            whetherLoginSuccessfully = false;
        }
        if (!userManger.ExamineValidityOfPassWordWhenLogin(userLoginPage.GetUserName(), userLoginPage.GetPassWord())) {
            userLoginPage.getLoginFunctionPanel().EstablishWarnForPassWords(userManger.getFeedBackForPasswordInLogin());
            whetherLoginSuccessfully = false;
        }
        if (whetherLoginSuccessfully) {
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

    private void DealWithTheDIYSetting() {
        boolean whetherDIYSucceed = true;
        UpdateTheCoordinateSetInTheControllingCenter();
        controllingCenter.setSkin(skin);
        boardSizeDIYPage.CleanExistingWarning();
        if (boardSizeDIYPage.getWhetherTourist()) {
            if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().isEmpty()) {
                boardSizeDIYPage.EstablishWarn("You should at least choose two blocks!");
                whetherDIYSucceed = false;
            } else if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().size() < 2) {
                boardSizeDIYPage.EstablishWarn("You should at least choose two blocks!");
                whetherDIYSucceed = false;
            } else {
                if (boardSizeDIYPage.getWhetherTimeLimited()) {
                    if (boardSizeDIYPage.GetTimeLimitation().isEmpty()) {
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
                            } else if (timeLimit <= 0) {
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
        } else if (!boardSizeDIYPage.getWhetherTimeLimited()) {
            if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().isEmpty()) {
                boardSizeDIYPage.EstablishWarn("You should at least choose two blocks!");
                whetherDIYSucceed = false;
            } else if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().size() < 2) {
                boardSizeDIYPage.EstablishWarn("You should at least choose two blocks!");
                whetherDIYSucceed = false;
            } else {
                if (boardSizeDIYPage.GetTargetWinningScore().isEmpty()) {
                    boardSizeDIYPage.EstablishWarn("The target score can not be null!");
                    whetherDIYSucceed = false;
                } else {
                    if (boardSizeDIYPage.GetTargetWinningScore().length() > 7) {
                        boardSizeDIYPage.EstablishWarn("Target score is too large!");
                        whetherDIYSucceed = false;
                    }
                    try {
                        int targetScore = Integer.parseInt(boardSizeDIYPage.GetTargetWinningScore());
                        if (targetScore > 80000) {
                            boardSizeDIYPage.EstablishWarn("Target score should be no more than 8000!");
                            whetherDIYSucceed = false;
                        } else if (targetScore <= 0) {
                            boardSizeDIYPage.EstablishWarn("Target score should be positive!");
                            whetherDIYSucceed = false;
                        }
                    } catch (NumberFormatException e) {
                        boardSizeDIYPage.EstablishWarn("There exists invalid characters in target score!");
                        whetherDIYSucceed = false;
                    }
                }
            }
        } else if (boardSizeDIYPage.getWhetherTimeLimited()) {
            if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().isEmpty()) {
                boardSizeDIYPage.EstablishWarn("You should at least choose two blocks!");
                whetherDIYSucceed = false;
            } else if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().size() < 2) {
                boardSizeDIYPage.EstablishWarn("You should at least choose two blocks!");
                whetherDIYSucceed = false;
            } else {
                if (boardSizeDIYPage.getWhetherTimeLimited()) {
                    if (boardSizeDIYPage.GetTimeLimitation().isEmpty()) {
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
                            } else if (timeLimit <= 0) {
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
        }
        if (whetherDIYSucceed && !boardSizeDIYPage.getWhetherTourist() && !boardSizeDIYPage.getWhetherTimeLimited() && !boardSizeDIYPage.isWhetherCompetition()) {
            UserManger userManger = new UserManger();
            if (boardSizeDIYPage.GetArchiveName().isEmpty()) {
                boardSizeDIYPage.EstablishWarn("The archive name can not be null!");
                whetherDIYSucceed = false;
            } else if (boardSizeDIYPage.GetArchiveName().length() > 16) {
                boardSizeDIYPage.EstablishWarn("The archive name is too long!");
                whetherDIYSucceed = false;
            } else if (!userManger.ExaminePassWordsWhetherOnlyLetterNumber(boardSizeDIYPage.GetArchiveName())) {
                boardSizeDIYPage.EstablishWarn("There exists invalid characters in your archive name!");
                whetherDIYSucceed = false;
            } else if (userManger.ExamineWhetherArchiveAlreadyExisted(user, boardSizeDIYPage.GetArchiveName())) {
                boardSizeDIYPage.EstablishWarn("Archive name already existed!");
                whetherDIYSucceed = false;
            }
        }
        if (!boardSizeDIYPage.getWhetherTourist()) {
            if (whetherDIYSucceed) {
                this.remove(boardSizeDIYPage);
                controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
                if (boardSizeDIYPage.getWhetherTimeLimited()) {
                    this.LoadInGamePageForUserWithTimeLimitationPractice(boardSizeDIYPage.GetTimeLimit());
                } else {
                    controllingCenter.setTargetWinningScore(Integer.parseInt(boardSizeDIYPage.GetTargetWinningScore()));
                    UserManger userManger = new UserManger();
                    controllingCenter.setArchiveName(boardSizeDIYPage.GetArchiveName());
                    userManger.SaveGameBoardToASpecificArchivePracticeWithoutTimeLimit(user, boardSizeDIYPage.GetArchiveName(), controllingCenter);
                    user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
                    this.LoadInGamePageForUserWithoutTimeLimitationPractice();
                }
                boardSizeDIYPage = null;
                repaint();
                setVisible(true);
            }
        } else {
            if (whetherDIYSucceed) {
                this.remove(boardSizeDIYPage);
                controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
                if (boardSizeDIYPage.getWhetherTimeLimited()) {
                    this.LoadInGamePageForTouristWithTimeLimitation(boardSizeDIYPage.GetTimeLimit());
                } else {
                    this.LoadInGamePageForTouristWithoutTimeLimitation();
                }
                boardSizeDIYPage = null;
                repaint();
                setVisible(true);
            }
        }
    }

    private void SetUpTheLoginUser() {
        user = new User(userLoginPage.GetUserName());
    }

    private void DealWithArchiveInput() {
        boolean whetherArchiveAvailable = true;
        boolean whetherDefault = false;
        if (askingForArchivePanel.GetArchiveName().isEmpty()) {
            if (!user.ExamineWhetherArchiveAlreadyExisted("WhenYouHaveSomethingToSaySilenceIsALie")) {
                askingForArchivePanel.EstablishWarn("There is no such archive!");
                whetherArchiveAvailable = false;
            } else {
                whetherDefault = true;
            }
        } else if (!user.ExamineWhetherArchiveAlreadyExisted(askingForArchivePanel.GetArchiveName())) {
            askingForArchivePanel.EstablishWarn("There is no such archive!");
            whetherArchiveAvailable = false;
        }  else {
            try {
                FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + askingForArchivePanel.GetArchiveName()+ ".txt");
                Properties properties = new Properties();
                properties.load(inputStream);
                inputStream.close();
            } catch (IOException e){
                askingForArchivePanel.EstablishWarn("Archive disappeared! Error Code: 101");
                whetherArchiveAvailable = false;
                user.DeleteArchiveInList(askingForArchivePanel.GetArchiveName());
            }
        }
        if (whetherArchiveAvailable){
        if (user.WhetherInvalidlyModified(askingForArchivePanel.GetArchiveName())) {
            user.DeleteCompleteArchive(askingForArchivePanel.GetArchiveName());
            askingForArchivePanel.EstablishWarn("Invalid archive! Error Code: 103");
            whetherArchiveAvailable = false;
        }}
        if (whetherArchiveAvailable) {
            controllingCenter = new ControllingCenter();
            if (whetherDefault) {
                user.BuildControllingCenterBasedOnTheArchive("WhenYouHaveSomethingToSaySilenceIsALie", controllingCenter);
            } else {
                user.BuildControllingCenterBasedOnTheArchive(askingForArchivePanel.GetArchiveName(), controllingCenter);
            }
            this.remove(askingForArchivePanel);
            askingForArchivePanel = null;
            this.LoadInGamePageForUserWithoutTimeLimitationPractice();
            repaint();
            setVisible(true);
        }
    }

    public void DealWithDefaultThreeInPractice() {
        if (user.ExamineWhetherArchiveAlreadyExisted("WhenYouHaveSomethingToSaySilenceIsALie")) {
            user.DeleteCompleteArchive("WhenYouHaveSomethingToSaySilenceIsALie");
        }
        controllingCenter = new ControllingCenter();
        controllingCenter.setSkin(skin);
        this.UpdateTheCoordinateSetInTheControllingCenterForThree();
        controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
        controllingCenter.setTargetWinningScore(2048);
        UserManger userManger = new UserManger();
        controllingCenter.setArchiveName("WhenYouHaveSomethingToSaySilenceIsALie");
        userManger.SaveGameBoardToASpecificArchivePracticeWithoutTimeLimit(user, controllingCenter.getArchiveName(), controllingCenter);
        user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
        this.LoadInGamePageForUserWithoutTimeLimitationPractice();
        boardSizeDIYPage = null;
        repaint();
        setVisible(true);
    }

    public void DealWithDefaultFourInPractice() {
        if (user.ExamineWhetherArchiveAlreadyExisted("WhenYouHaveSomethingToSaySilenceIsALie")) {
            user.DeleteCompleteArchive("WhenYouHaveSomethingToSaySilenceIsALie");
        }
        controllingCenter = new ControllingCenter();
        controllingCenter.setSkin(skin);
        this.UpdateTheCoordinateSetInTheControllingCenterForFour();
        controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
        controllingCenter.setTargetWinningScore(2048);
        UserManger userManger = new UserManger();
        controllingCenter.setArchiveName("WhenYouHaveSomethingToSaySilenceIsALie");
        userManger.SaveGameBoardToASpecificArchivePracticeWithoutTimeLimit(user, controllingCenter.getArchiveName(), controllingCenter);
        user.SavingCellValueSavingForCurrentStep(controllingCenter.getArchiveName(), controllingCenter);
        this.LoadInGamePageForUserWithoutTimeLimitationPractice();
        boardSizeDIYPage = null;
        repaint();
        setVisible(true);
    }

    private void OpenGameRoom() {
        this.serverName = user.getUserName();
        String IPAddress = this.FindIpForComputer();
        this.remove(whetherNewGameRoomPage);
        whetherNewGameRoomPage = null;
        this.LoadSuccessfullyCreateGameRoomWaitingPageForServer(IPAddress);
        repaint();
        setVisible(true);
    }

    private void DealWithCreatingGameRoom() {
        serverRunnable = new ServerRunnable(user, this);
        serverThread = new Thread(serverRunnable);
        serverThread.start();
        while (!whetherSuccessfullyConnected) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (whetherSuccessfullyConnected) {
                break;
            }
        }
        successfullyCreateGameRoomWaitingPage.UpdatePanelForClient();
        successfullyCreateGameRoomWaitingPage.UpdateBottomPanel();
        successfullyCreateGameRoomWaitingPage.getContinuePanel().addMouseListener(this);
        repaint();
        setVisible(true);
    }

    private void DealWithEnteringGameRoom() {
        this.clientName = user.getUserName();
        clientRunnable = new ClientRunnable(enterGameRoomPage.GetIP(), user, this);
        this.IPOfServer = enterGameRoomPage.GetIP();
        clientThread = new Thread(clientRunnable);
        clientThread.start();
        while (!whetherSuccessfullyConnected) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (whetherSuccessfullyConnected) {
                break;
            }
        }
        this.remove(enterGameRoomPage);
        LoadSuccessfullyCreateGameRoomWaitingPageForClient(enterGameRoomPage.GetIP(), this.serverName);
        enterGameRoomPage = null;
        repaint();
        setVisible(true);
    }

    private boolean whetherAStringIsANumber(String targetString) {
        boolean whetherAnNumber = true;
        for (int inDexInString = 0; inDexInString < targetString.length(); inDexInString++) {
            if (!Character.isDigit(targetString.charAt(inDexInString))) {
                whetherAnNumber = false;
                return whetherAnNumber;
            }
        }
        return whetherAnNumber;
    }

    private String FindIpForComputer() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();

                    if (inetAddress.getAddress().length == 4 && !inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No Net Work";
    }
}