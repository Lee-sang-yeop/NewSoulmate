package tk.newsoulmate.web.support.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "SupportVerify", value = "/support/verify")
public class SupportVerifyController extends HttpServlet {

	private final SupportService supportService;

	public SupportVerifyController() {
		this.supportService = new SupportService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달한 값을 받기위한 파라미터
		// get 메소드로 전달한 값을 받음, impUid로 iamport에 요청해서 결제정보를 다시 받아옴
		String impUid = request.getParameter("impUid"); // 5. impuid로 얻어올수 있는 정보는 아임포트에 요청해야함
		String merchantUid = request.getParameter("merchantUid"); // 우리쪽
		boolean result = supportService.verify(impUid, merchantUid); // 양쪽에서 얻어온 정보가 같은지 6. 검증요청 같으면 true 다르면
		if (result) {
			supportService.complete(merchantUid); // 성공했으면 서포트 상태를 complete로
			response.setStatus(200);
		} else {
			supportService.failed(merchantUid); // 잘못됐으면 failed
			response.setStatus(400); // Bad Request
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
