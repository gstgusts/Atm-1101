package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    JPanel actionPanel;
    JPanel mainPanel;
    CardLayout card;

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

        card = new CardLayout();
        mainPanel.setLayout(card);

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
            card.next(mainPanel);
        });
        startPanel.add(btnStart);
        mainPanel.add(startPanel);
    }

    private void initAtm() {

        ArrayList<BankAccount> accounts = new ArrayList<>();

        var client = new Client("Gusts","Link", "123");

        var bankAccount1 = new BankAccount("111", 100, client);

        accounts.add(bankAccount1);

        atm = new Atm(10000, accounts);
    }
}
