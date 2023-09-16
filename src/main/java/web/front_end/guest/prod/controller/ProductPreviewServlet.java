package web.front_end.guest.prod.controller;

import static web.front_end.guest.prod.util.OpaProductConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.util.CommonUtil;

@WebServlet("/api/products/preview/*")
public class ProductPreviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] path = request.getPathInfo().split("/");
        if(path.length < 2) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        int opaProdNo = Integer.parseInt(path[1]);
        byte[] opaProdPicture = SERVICE.findProductPicture(opaProdNo);
        if (opaProdPicture == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setContentType("image/jpeg");
        response.setContentLength(opaProdPicture.length);
        response.getOutputStream().write(opaProdPicture);
    }
}