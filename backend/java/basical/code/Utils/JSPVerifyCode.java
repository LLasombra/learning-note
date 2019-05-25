package Servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 *
 *  core code:
 *       // 1.获取图片缓冲区
 *       BufferedImage bufferedImage=new BufferedImage(80, 40, BufferedImage.TYPE_3BYTE_BGR);
 *       // 2.获取画笔
 *       Graphics2D graphics2d=(Graphics2D) bufferedImage.getGraphics();
 *       graphics2d.setColor(Color.RED);//第一次设置的是画板的颜色
 *       // 3.设置属性：颜色，字体、填充等
 *       graphics2d.fillRect(0, 0, 80, 40);
 *      graphics2d.setColor(Color.green);//第一次设置的是字体的颜色
 *       graphics2d.setFont(new Font("宋体",Font.BOLD,10));
 *       graphics2d.drawString("hello world", 5	, 20);
 *       // 4.输出图片
 *       ImageIO.write(bufferedImage, "JPG", new FileOutputStream("a.jpg"));
 */
@SuppressWarnings("serial")
public class VerifyCode extends HttpServlet {
    public VerifyCode() {
        super();
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        //s声明一个BufferedImage
        BufferedImage bim=new BufferedImage(78, 18, BufferedImage.TYPE_INT_BGR );
        //利用Graphics控制
        Graphics g=bim.getGraphics();
        Random random=new Random();
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, 78, 18);
        //产生4个随机数
        StringBuffer sbf=new StringBuffer();
        for(int i=0;i<4;i++){
            g.setColor(Color.pink);
            g.setFont(new Font("华文隶书", Font.BOLD|Font.ITALIC, 22));
            int n=random.nextInt(10);
            g.drawString(""+n, i*17+6, 17);
            sbf.append(n);
        }
        //生成验证码保存在session中
        HttpSession session=request.getSession(true);
        session.setAttribute("piccode", sbf);
        //禁止缓存
        response.setHeader("Prama", "no-cathe");
        response.setHeader("Coche-control", "no-cathe");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //writeImage
        ImageIO.write(bim, "JPG", response.getOutputStream());
        g.dispose();
        response.getOutputStream().close();
        /*//由于这里出现了异常，所以下面的重定向代码并没有执行
        //request.getRequestDispatcher("../bstore/Login.jsp").forward(request, response);
        //try{
        //request.getRequestDispatcher("../bstore/Login.jsp").forward(request, response);
        response.sendRedirect("../bstore/Login.jsp");
        return;}catch(Exception e){
            //e.printStackTrace();
        //}	*/
    }

    private Color getRandColor(int fc,int bc) {
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                doGet(request, response);
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }
}
