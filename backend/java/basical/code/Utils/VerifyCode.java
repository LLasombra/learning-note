package Servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * ****使用：
 *       VerifyCode2 verifyCode=new VerifyCode2();
 *       BufferedImage image=verifyCode.getImage();
 *       VerifyCode2.output(image, new FileOutputStream("2.jpg"));
 */
public class VerifyCode2 {
    private int width = 70; // 图片的宽度
    private int height = 35; // 图片的高度
    private int num=3; // 图片上斜线数量
    private int count=4; // 图片上字符的个数
    private String[] fontNames  = {"宋体", "Consolas", "黑体", "微软雅黑", "Arial"};
    private String codes  = "0123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPOQRSTUVWXYZ";
    private Color bgColor  = new Color(255, 255, 255);
    private String text ; // 具体内容

    private Random r = new Random(); //一个Random对象

    public BufferedImage getImage () {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        g2.setColor(this.bgColor);  //图片颜色
        g2.fillRect(0, 0, width, height);

        // 向图片中画4个字符
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++)  {
            int index = r.nextInt(codes.length());
            String s= codes.charAt(index)+"";
            sb.append(s);

            float x = i * 1.0F * width / count;
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s, x, height-5);
        }
        this.text = sb.toString();

        // 设置斜线
        drawLine(image);
        return image;
    }

    public String getText () {
        return text;
    }

    public static void output (BufferedImage image, OutputStream out)
                throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

    private void drawLine (BufferedImage image) {
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        for(int i = 0; i < num; i++) {
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(width);
            int y2 = r.nextInt(height);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(Color.BLUE);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private Color randomColor () {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }

    private Font randomFont () {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = r.nextInt(4);
        int size = r.nextInt(5) + 24;
        return new Font(fontName, style, size);
    }

}
