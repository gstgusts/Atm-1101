package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class AtmForm extends JFrame {

    JPanel startPanel;
    JButton btnStart;

    JPanel pinPanel;
    JLabel lblInfo;
    JPasswordField pfPin;
    JButton btnCheckPin;
    JLabel lblPinResult;
    JButton btnGoToStart;

    JPanel accountPanel;
    JList lstAccounts;

    JPanel actionPanel;
    JPanel mainPanel;
    CardLayout cardLayout;

    Card card;

    Atm atm;

    public AtmForm() {
        super("Atm");
        setSize(300, 320);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initAtm();
    }

    public void open() {
        initUi();
        setVisible(true);
    }

    private void initUi() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);

        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        initStartPanel();
        initPinPanel();
        initAccountPanel();
        initActionPanel();

        setContentPane(mainPanel);
    }

    private void initActionPanel() {
        actionPanel = new JPanel();
        actionPanel.setBackground(Color.darkGray);
        mainPanel.add(actionPanel);
    }

    private void initAccountPanel() {
        accountPanel = new JPanel();
        accountPanel.setBackground(Color.gray);

        lstAccounts = new JList();

        accountPanel.add(lstAccounts);

        mainPanel.add(accountPanel);
    }

    private void initPinPanel() {
        pinPanel = new JPanel();
        pinPanel.setBackground(Color.lightGray);
        pinPanel.setLayout(new GridLayout(4,1));

        lblInfo = new JLabel("ENTER PIN");
        pfPin = new JPasswordField();
        btnCheckPin = new JButton("CONTINUE");
        lblPinResult = new JLabel();

        btnCheckPin.addActionListener(a -> {
             var pin = new String(pfPin.getPassword());
             var checkResult = atm.isPinValid(Integer.valueOf(pin));

             if(!checkResult) {
                lblPinResult.setText("INCORRECT PIN");
                return;
             }

            var accountStream = StreamSupport.stream(atm.getBankAccounts().spliterator(), false);
            var accounts = accountStream.map(account -> account.getNumber()).toArray();

            lstAccounts.setListData(accounts);

            cardLayout.next(mainPanel);
        });

        pinPanel.add(lblInfo);
        pinPanel.add(pfPin);
        pinPanel.add(btnCheckPin);
        pinPanel.add(lblPinResult);

        mainPanel.add(pinPanel);
    }

    private void initStartPanel() {
        startPanel = new JPanel();
        startPanel.setBackground(Color.white);
        btnStart = new JButton("Start");
        btnStart.addActionListener(a -> {
            atm.enterCard(card);
            cardLayout.next(mainPanel);
        });
        startPanel.add(btnStart);
        mainPanel.add(startPanel);
    }

    private void initAtm() {

        ArrayList<BankAccount> accounts = new ArrayList<>();

        var client = new Client("Gusts","Link", "123");

        var bankAccount1 = new BankAccount("111", 100, client);
        var bankAccount2 = new BankAccount("112", 200, client);

        card = new Card(1111, "1212 1313 1414 1515", client);

        accounts.add(bankAccount1);
        accounts.add(bankAccount2);

        atm = new Atm(10000, accounts);
    }
}
