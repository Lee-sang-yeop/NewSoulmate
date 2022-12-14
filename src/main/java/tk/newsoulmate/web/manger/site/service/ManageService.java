package tk.newsoulmate.web.manger.site.service;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

import java.sql.Connection;
import java.util.ArrayList;

import tk.newsoulmate.domain.dao.*;
import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.domain.type.MemberGrade;

public class ManageService {

    private MemberDao memberDao = new MemberDao();


    public ArrayList<Member> selectMemberList(PageInfo pageInfo, String filter) {
        Connection conn = getConnection();
        ArrayList<Member> mList;
        if (filter.equals("ALL")) {
            mList = memberDao.selectMemberList(conn, pageInfo);
        } else {
            mList = memberDao.selectMemberListByFilter(conn, pageInfo, MemberGrade.valueOf(filter));
        }
        close();
        return mList;
    }

    public int countMember(String filter) {
        Connection conn = getConnection();
        int count = 0;
        if (filter.equals("ALL")) {
            count = memberDao.count(conn);
        } else {
            count = memberDao.countByFilter(conn, MemberGrade.valueOf(filter));
        }
        close();
        return count;
    }




/*    manageMemberList 페이징바 처리 관련

        public int selectMemberListCount(){
        Connection conn = getConnection();

        int listCount = new MemberDao().selectMemberListCount(conn);

        close();
        return listCount;
    }


    public ArrayList<ManageMember> selectMemberList(PageInfo pi) {
        Connection conn = getConnection();
        ArrayList<ManageMember> mList = memberDao.selectMemberList(conn,pi);
        close();
        return mList;
    }
    */











    public ArrayList<Member> selectManageMember() {
        Connection conn = getConnection();
        ArrayList<Member> mList = memberDao.selectManageMember(conn);
        close();
        return mList;
    }

    public ArrayList<GradeUp> selectGradeUp() {
        Connection conn = getConnection();
        ArrayList<GradeUp> gList = new GradeUpDao().selectAllGrade(conn);

        AttachmentDao at = new AttachmentDao();
        //new AttachmentDao().selectGradeUpAttachment(conn,gList);
        at.selectGradeUpAttachment(conn, gList);
        close();
        return gList;
    }
    public boolean isSubmit(int memberNo) {
        Connection conn = getConnection();
        boolean result= new GradeUpDao().isSubmit(conn,memberNo);
        close();
        return result;
    }


    public int selectAdoptApplyListCount(){
        Connection conn = getConnection();

        int listCount = new SubscriptionDao().selectAdoptApplyListCount(conn);

        close();
        return listCount;
    }

    public int selectReportListCount(){
        Connection conn = getConnection();

        int listCount = new ReportDao().selectReportListCount(conn);

        close();
        return listCount;
    }
    public ArrayList<Subscription> selectAdoptApplyList(PageInfo pi){
        Connection conn = getConnection();

        ArrayList<Subscription> list = new SubscriptionDao().selectAdoptApplyList(conn, pi);
        close();
        return list;
    }
    public ArrayList<Report> selectReportList(){
        Connection conn = getConnection();
        ArrayList<Report> rList = new ReportDao().selectReportList(conn);
        close();
        return rList;
    }

    public Subscription selectAdoptApplyDetail(int subNo){
        Connection conn = getConnection();

        Subscription s = new SubscriptionDao().selectAdoptApplyDetail(conn,subNo);
        close();

        return s;
    }

    public Report selectReportDetail(int refNo){
        Connection conn = getConnection();
        Report r = new ReportDao().selectReportDetail(conn,refNo);
        close();
        return r;
    }


    public int changeReportStatus(int reportNo){
        Connection conn = getConnection();
        int result = new ReportDao().changeReportStatus(conn, reportNo);
        if(result >0){
            commit();
        } else{
            rollback();
        }
        close();
        return result;
    }

    public Subscription selectAdoptApplyListCheck(int subNo){
        Connection conn = getConnection();

        Subscription s = new SubscriptionDao().selectAdoptApplyDetail(conn,subNo);

        close();

        return s;
    }

    public int changeStatus(String[] memberNoArr) {
        Connection conn = getConnection();
        int result1 = new GradeUpDao().changeGrade(conn,memberNoArr);
        int result2 = new MemberDao().changeGrade(conn,memberNoArr);

        if(result1 == memberNoArr.length && result2 == memberNoArr.length){
            commit();
        }else{
            rollback();
        }
        close();
        return (result1+result2)/2;
    }

    public int changeStatusReject(String[] memberNo) {
        Connection conn = getConnection();
        int result1 = new GradeUpDao().changeGrade(conn,memberNo);

        if(result1 == memberNo.length){
            commit();
        }else{
            rollback();
        }
        close();
        return result1;
    }


    public Notice selectNotice(long animalNo) {
        Connection conn=getConnection();
        Notice n=new NoticeDao().selectNotice(conn,animalNo);
        close();
        return n;
    }
    public int changeAdoptApplySubRead(int subNo){
        Connection conn = getConnection();
        int result = new SubscriptionDao().changeAdoptApplySubRead(conn,subNo);
        if(result > 0){
            commit();
        } else{
            rollback();
        }
        close();
        return result;
    }

}
