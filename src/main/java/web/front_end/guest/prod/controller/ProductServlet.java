package web.front_end.guest.prod.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.front_end.guest.prod.util.OpaProductConstants.SERVICE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import core.util.CommonUtil;
import web.front_end.guest.prod.entity.OpaPrcats;
import web.front_end.guest.prod.entity.OpaProducts;

@WebServlet("/api/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        response.setCharacterEncoding("UTF-8");
        if (type != null) {
            if (type.equals("search")) {
                doSearch(request, response);
                return;
            } else if (type.equals("category")) {
                doCategory(request, response);
                return;
            }
        }
        PrintWriter out = response.getWriter();
        out.print("{\"success\": false, \"message\": \"Invalid request\"}");
        out.flush();
    }

    void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryFilters[] = request.getParameterValues("category");
        String minPrice = request.getParameter("min-price");
        String maxPrice = request.getParameter("max-price");
        String page = request.getParameter("page");
        int productPerPage = 10;
        if(page == null) {
            page = "1";
        }
        HashSet<Integer> categoryFilterSet = new HashSet<>();
        if (categoryFilters != null) {
            for (String categoryFilter : categoryFilters) {
                categoryFilterSet.add(Integer.parseInt(categoryFilter));
            }
        }

        Long productCount = SERVICE.countProducts(categoryFilters, minPrice, maxPrice);
        Long total_page = productCount / productPerPage + (productCount % productPerPage == 0 ? 0 : 1);
        List<OpaProducts> opaProducts = SERVICE.findAllProducts(categoryFilters, minPrice, maxPrice, page,
                productPerPage);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("current_page", page);
        jsonObject.addProperty("total_page", total_page);
        JsonArray jsonArray = new JsonArray();

        for (OpaProducts opaProduct : opaProducts) {
            JsonObject productJson = new JsonObject();
            productJson.addProperty("id", opaProduct.getOpaProdNo());
            productJson.addProperty("name", opaProduct.getOpaProdName());
            productJson.addProperty("price", opaProduct.getOpaProdPrice());
            productJson.addProperty("image", OpaProducts.getPreviewURL(opaProduct));
            productJson.addProperty("description", opaProduct.getOpaProdContent());
            productJson.addProperty("category", opaProduct.getOpaPrcats().getOpaPrcatsNo());
            productJson.addProperty("url", opaProduct.getOpaProdUrl());
            jsonArray.add(productJson);
        }
        jsonObject.add("products", jsonArray);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
    }

    void doCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OpaPrcats> opaPrcats = SERVICE.findAllCategory();
        for (OpaPrcats opaPrcat : opaPrcats) {
            opaPrcat.setOpaProductses(null);
        }
        writePojo2Json(response, opaPrcats);
    }
}