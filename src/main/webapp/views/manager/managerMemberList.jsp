<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.type.MemberGrade" %>
<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.web.member.service.MemberService" %>
<%@ page import="tk.newsoulmate.domain.vo.response.ManageMemberResponse" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-16
  Time: 오후 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<ManageMemberResponse> mList = (ArrayList<ManageMemberResponse>)request.getAttribute("mList");
    List<ManageMemberResponse> filteredList = new ArrayList<>(mList);
    ManageService ms = new ManageService();
%>
<html>
<head>
    <title>회원관리-회원리스트</title>
    <link href="<%=request.getContextPath()%>/css/manager/managerMemberList.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp" %>
</head>
<body>
<header>
    <%@include file="/views/manager/managerHeader.jsp" %>
</header>


<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                총 회원 수
                <span id="countMember" style="color: #f45d48;"><%= ms.selectCountMember() %></span> 명
            </div>

            <div class="box">
                회원등급
                <span id="memberGrade">
                        <select name="selectMemberGrade" id="selectMemberGrade" onchange="changeSelect(this.options[this.selectedIndex].value)">
                            <option value="ALL">전체</option>
                            <option value="USER">일반회원</option>
                            <option value="SHELTER_MANAGER">보호소 관계자</option>
                        </select>
                </span>
                <span id="gray">(선택된 회원만 조회됩니다)</span>
            </div>

            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th>회원번호</th>
                        <th>이름</th>
                        <th>아이디</th>
                        <th>이메일</th>
                        <th>보호소이름</th>
                        <th>등급</th>
                        <th>가입일</th>
                        <th>최근접속일</th>
                        <th>관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%if (filteredList == null || filteredList.isEmpty()) {%>
                    <tr>
                        <td colspan="9">존재하는 회원내역이 없습니다.</td>
                    </tr>

                    <%} else { %>

                    <%for (ManageMemberResponse m : filteredList) {%>
                    <tr>
                        <td><%=m.getMemberNo() %>
                        </td>
                        <td><%=m.getMemberName() %>
                        </td>
                        <td><%=m.getMemberId() %>
                        </td>
                        <td><%=m.getEmail() %>
                        </td>
                        <td><%=m.getShelterName()%>

                        </td>
                        <td>
                            <%if (m.getMemberGrade() == MemberGrade.valueOfNumber(2)) { %>
                            관리자
                            <%} else if (m.getMemberGrade() == MemberGrade.valueOfNumber(1)) { %>
                            보호소관계자
                            <%} else { %>
                            일반회원
                            <%} %>
                        </td>
                        <td><%=m.getEnrollDate() %>
                        </td>
                        <td><%=m.getResentConnection()%>
                        </td>
                        <td>
                            <div>
                                <button onclick="updateGradeMember(<%=m.getMemberNo()%>, '<%=m.getMemberGrade().name()%>')">
                                    등급변경
                                </button>
                                <button onclick="deleteMember(<%=m.getMemberNo()%>)">탈퇴</button>
                            </div>
                        </td>
                    </tr>
                    <%} %>
                    <%} %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    window.onload = () => {
        $("#selectMemberGrade").val('<%=request.getParameter("filter")%>').prop("selected", true);
    }

    function changeSelect(selected) {
        location.href = "${context}/manageMember?page=1&filter=" + selected
    }

    function updateGradeMember(memberNo, _memberGrade) {
        let memberGrade;
        if (_memberGrade === 'USER') {
            memberGrade = 'SHELTER_MANAGER';
        } else if (_memberGrade === 'SHELTER_MANAGER') {
            memberGrade = 'USER';
        }
        $.ajax({
            url: "<%=request.getContextPath()%>/manage/grade",
            type: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                'memberNo': memberNo,
                'memberGrade': memberGrade // USER, SHELTER_MANAGER
            }),
            success: function () {
                alert("수정이 완료되었습니다.");
                location.reload();
            },
            error: function () {
                alert("수정 중 오류가 발생하였습니다.(error)");
            }
        });
    }

    function deleteMember(memberNo) {
        if (confirm("정말로 탙퇴처리 하시겠습니까?")) {
            $.ajax({
                url: "<%=request.getContextPath()%>/manage/delete-member",
                type: 'post',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    'memberNo': memberNo,
                }),
                success: function () {
                    alert("탈퇴처리가 완료되었습니다.");
                    location.reload();
                },
                error: function () {
                    alert("탈퇴 처리중 오류가 발생하였습니다.(error)");
                }
            })
        } else {

        }
    }
</script>

</body>
</html>
