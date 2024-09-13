package com.techlabs.bank.config;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.techlabs.bank.dto.TransactionDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static byte[] generatePassbookPdf(List<TransactionDto> transactions) throws IOException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Passbook Report"));
            document.add(new Paragraph(" "));
            
            for (TransactionDto transaction : transactions) {
                document.add(new Paragraph(String.format(
                    "Transaction ID: %d, Type: %s, Amount: %.2f, Date: %s",
                    transaction.getTransactionId(),
                    transaction.getTransactionType(),
                    transaction.getAmount(),
                    transaction.getDate()
                )));
                document.add(new Paragraph(" "));
            }
        } finally {
            document.close();
        }

        return baos.toByteArray();
    }
}
