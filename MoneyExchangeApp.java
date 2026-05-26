package codsoft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MoneyExchangeApp extends JFrame implements ActionListener {

    JLabel titleLabel;
    JLabel amountLabel;
    JLabel fromLabel;
    JLabel toLabel;

    JTextField amountField;

    JComboBox<String> fromBox;
    JComboBox<String> toBox;

    JButton convertButton;
    JButton resetButton;

    JTextArea outputArea;

    String currencies[] = {
            "USD", "INR", "EUR", "GBP", "JPY",
            "AUD", "CAD", "CHF", "CNY"
    };

    public MoneyExchangeApp() {

        setTitle("Money Exchange Calculator");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        titleLabel = new JLabel("LIVE CURRENCY CONVERTER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        topPanel.add(titleLabel);

        c.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 15, 15));

        amountLabel = new JLabel("Enter Amount");
        amountField = new JTextField();

        fromLabel = new JLabel("Base Currency");
        fromBox = new JComboBox<>(currencies);

        toLabel = new JLabel("Target Currency");
        toBox = new JComboBox<>(currencies);

        convertButton = new JButton("Convert Currency");
        resetButton = new JButton("Clear Data");

        convertButton.addActionListener(this);
        resetButton.addActionListener(this);

        centerPanel.add(amountLabel);
        centerPanel.add(amountField);

        centerPanel.add(fromLabel);
        centerPanel.add(fromBox);

        centerPanel.add(toLabel);
        centerPanel.add(toBox);

        centerPanel.add(convertButton);
        centerPanel.add(resetButton);

        c.add(centerPanel, BorderLayout.CENTER);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 15));

        JScrollPane pane = new JScrollPane(outputArea);
        pane.setPreferredSize(new Dimension(500, 140));

        c.add(pane, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == convertButton) {
            performConversion();
        }

        if (e.getSource() == resetButton) {
            clearFields();
        }
    }

    public void performConversion() {

        try {

            String amountText = amountField.getText().trim();

            if (amountText.length() == 0) {
                JOptionPane.showMessageDialog(this,
                        "Please enter amount");
                return;
            }

            double amount = Double.parseDouble(amountText);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Amount must be positive");
                amountField.setText("");
                return;
            }

            String fromCurrency = fromBox.getSelectedItem().toString();
            String toCurrency = toBox.getSelectedItem().toString();

            if (fromCurrency.equals(toCurrency)) {
                JOptionPane.showMessageDialog(this,
                        "Choose different currencies");
                return;
            }

            double exchangeRate = getExchangeRate(fromCurrency, toCurrency);

            double convertedAmount = amount * exchangeRate;

            StringBuilder result = new StringBuilder();

            result.append("==============================\n");
            result.append("Currency Conversion Result\n");
            result.append("==============================\n\n");

            result.append("Base Currency      : ")
                    .append(fromCurrency)
                    .append("\n");

            result.append("Target Currency    : ")
                    .append(toCurrency)
                    .append("\n");

            result.append("Exchange Rate      : ")
                    .append(exchangeRate)
                    .append("\n");

            result.append("Entered Amount     : ")
                    .append(amount)
                    .append("\n");

            result.append("Converted Amount   : ")
                    .append(convertedAmount)
                    .append("\n");

            outputArea.setText(result.toString());
        }

        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Only numeric values are allowed");
            amountField.setText("");
        }

        catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(this,
                    "Internet connection not available");
        }

        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    "API endpoint not found");
        }

        catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Unable to fetch exchange rates");
        }

        catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Unexpected error occurred");
        }
    }

    public double getExchangeRate(String from, String to) throws Exception {

        String apiLink =
                "https://open.er-api.com/v6/latest/" + from;

        URL url = new URL(apiLink);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new IOException();
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        String line;

        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        String jsonData = response.toString();

        String searchKey = "\"" + to + "\":";

        int start = jsonData.indexOf(searchKey);

        if (start == -1) {
            throw new Exception();
        }

        start = start + searchKey.length();

        int end = jsonData.indexOf(",", start);

        String rateText = jsonData.substring(start, end);

        return Double.parseDouble(rateText);
    }
    public void clearFields() {

        amountField.setText("");

        outputArea.setText("");

        fromBox.setSelectedIndex(0);

        toBox.setSelectedIndex(1);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoneyExchangeApp();
            }
        });
    }	
}

