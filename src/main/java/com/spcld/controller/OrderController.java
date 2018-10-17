package com.spcld.controller;

import com.alibaba.fastjson.JSON;
import com.local.entity.Order;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharSet;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


//@RestController
@Controller
public class OrderController {


	@RequestMapping(value = "/order/add", method = RequestMethod.POST, consumes={"application/json;UTF-8","text/plain;UTF-8"}/*, produces={"application/json"}*/)
	@ResponseBody
	public Order add(HttpServletRequest request, HttpServletResponse response, @RequestBody String order) {

		try {
			ServletInputStream is = request.getInputStream();

			List<String> strings = IOUtils.readLines(is, StandardCharsets.UTF_8);

			for(String line : strings){
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		System.out.println("order is :" + order);
		//return JSON.toJSONString(new  Order(10086, new Date(), "这是返回的订单信息", true));
		return new  Order(10086, new Date(), "这是返回的订单信息", true);
	}

	@RequestMapping(value = "/order/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Order get(@PathVariable("id") Long id)
	{

		return new Order();
	}










	@RequestMapping(value = "/order/detail", method=RequestMethod.POST/*, consumes={"application/json;charset=utf-8"}, produces={"application/json;charset=utf-8"}*/)
	@ResponseBody
	public Order getOrderInfo(@RequestBody Order order, HttpServletRequest request){

		Enumeration<String> headerNames = request.getHeaderNames();
		Enumeration e1 = request.getHeaderNames();
		while (e1.hasMoreElements()) {
			String headerName = (String) e1.nextElement();
			String headValue = request.getHeader(headerName);
			System.out.println(headerName + "=" + headValue);
		}


		System.out.println("getOrderInfo source:" + order);

		return  new  Order(10086, new Date(), "这是返回的订单信息", true);

	}



	@RequestMapping(value = "/test/String2Order", method=RequestMethod.POST)
	@ResponseBody
	public String testString2Order(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order){

		System.out.println("come here !! Order info " + order);
		Set<MediaType> mimeTypeSet = new HashSet<>();
		mimeTypeSet.add(MediaType.APPLICATION_JSON);
		request.setAttribute("org.springframework.web.servlet.HandlerMapping.producibleMediaTypes",mimeTypeSet);

		return  JSON.toJSONString(new  Order(10086, new Date(), "这是返回的订单信息", true));
	}


	@RequestMapping(value = "/test/responseBody", method=RequestMethod.GET)
	@ResponseBody
	public Order testReqponseBody(HttpServletRequest request){

		System.out.println("come here !!");

		return  new  Order(10086, new Date(), "这是返回的订单信息", true);

	}


	/**
	 * 将返回对象以流的形式写入到 response 对象中
	 * 设置响应的请求头为 application/json，测试使用 feign 的调用方式（API 返回值类型为对象） 能否接受到
	 * @param request
	 * @param response
	 * @param order
	 */
	@RequestMapping(value = "/test/osresponse", method=RequestMethod.POST)
	public void testOutputStreamResponse(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order){

		System.out.println("come here !! Order info " + order);


		String responseMsg = JSON.toJSONString(new Order(10086, new Date(), "这是返回的订单信息", true));

		try {
			ServletOutputStream os = response.getOutputStream();
			byte[] bytes = responseMsg.getBytes(StandardCharsets.UTF_8);
			response.setContentType("application/json;charset=utf-8");
			response.setContentLength(bytes.length);
			os.write(bytes);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
