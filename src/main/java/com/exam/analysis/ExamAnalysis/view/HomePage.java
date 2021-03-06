package com.exam.analysis.ExamAnalysis.view;
import javax.swing.*;

public class HomePage {

    public HomePage() {
        init();
    }

    private void init() {
        JFrame homeFrame = new JFrame("Exam Analysis");

        homeFrame.setSize(1200, 800);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        homeFrame.add(panel);

        panel.setLayout(null);

        JButton studentButton = new JButton("Öğrenciler");
        studentButton.setBounds(20, 10, 100, 40);
        studentButton.addActionListener(e -> {
            homeFrame.setVisible(false);
            StudentPage student = new StudentPage();
        });

        JButton examButton = new JButton("Sınavlar");
        examButton.setBounds(130, 10, 100, 40);
        examButton.addActionListener(e -> {
            homeFrame.setVisible(false);
            ExamPage exam = new ExamPage();
        });

        JButton studentReports = new JButton("Rapor Oluştur");
        studentReports.setBounds(240, 10, 125, 40);
        studentReports.addActionListener(e -> {
            StudentReportPage studentReportPage = new StudentReportPage();
        });

        JLabel welcomeText = new JLabel("Hoşgeldiniz... Başlamak için bir butona tıklayın.");
        welcomeText.setBounds(20, 200, 300, 40);

        String studentText = "Öğrenciler butonunda öğrencilere ait bilgiler listelenir." +
                "'Öğrenci Ekle' diyerek yeni bir öğrenci ekleyebilirsiniz." +
                "Listedeki bir öğrenciye tıklayıp 'Öğrenci Detay' butonuna tıklarsanız o öğrenciye ait bilgileri görebilirsiniz." +
                "'Öğrenci Sınav Ekleme' butonu ile öğrenciye ait sınav ekleyebilirsiniz." +
                "Açılan sayfada 'Dosyadan Seç' butonu ile seçtiğiniz bir dosyadan istediğiniz sayıda öğrenciye ait bilgisi ekleyebilirsiniz.";
        JLabel studentPageIntro = new JLabel("<html>" + studentText + "<html>");
        studentPageIntro.setBounds(20, 250, 500, 100);

        String examText = "Sınavlar butonunda sınavlara ait bilgiler listelenir." +
                "'Sınav ekle' butonu ile yeni sınav ekleyebilirsiniz." +
                "Listedeki bir sınava tıklayıp 'Sınav Detay' butonuna tıklarsınız sınava dair detaylı bilgileri görebilirsiniz.";
        JLabel examPageIntro = new JLabel("<html>" + examText + "<html>");
        examPageIntro.setBounds(20, 360, 500, 100);

        String reportText = "Rapor oluştur butonu ile yeni bir pencere açılır ve bu penceredeki 'Gönder' butonu ile her öğrenci için ayrı dosyalar oluşturulup ilgili öğrenciye ait bilgiler bu dosyalara yazılır.";
        JLabel reportPageIntro = new JLabel("<html>" + reportText + "<html>");
        reportPageIntro.setBounds(20, 470, 500, 100);

        panel.add(studentButton);
        panel.add(examButton);
        panel.add(studentReports);
        panel.add(welcomeText);
        panel.add(studentPageIntro);
        panel.add(examPageIntro);
        panel.add(reportPageIntro);

        homeFrame.setVisible(true);
    }
}