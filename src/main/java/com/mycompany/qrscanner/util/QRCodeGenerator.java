package com.mycompany.qrscanner.util;

import com.mycompany.qrscanner.model.Employee;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {
    public static void generateQRCode(Employee employee) throws WriterException, IOException {
        String qrCodePath = "C:\\QRCodes\\";
        String qrCodeName = qrCodePath+employee.getFirstName()+employee.getId()+"-QRCODE.png";
        Path pathToDir = Paths.get(qrCodePath);
        if (!Files.exists(pathToDir)) {
            Files.createDirectories(pathToDir);
        }

        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                        "Firstname: "+employee.getFirstName()+ "\n"+
                        "Lastname: "+employee.getLastName()+ "\n"+
                        "Email: "+employee.getEmail()+ "\n" +
                        "Mobile: "+employee.getMobile(), BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
}
