package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.GradeUp;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.domain.type.BoardType;
import tk.newsoulmate.domain.type.ReplyType;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.close;

public class AttachmentDao {

    private Properties prop = new Properties();

    public AttachmentDao(){
        try {
            prop.loadFromXML(new FileInputStream(AttachmentDao.class.getResource("/sql/attachment/Attachment-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Attachment> selectBoardAttachment(Connection conn, int boardNo) {
        List<Attachment> aList =new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectBoardAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardNo);
            rset = psmt.executeQuery();
            Attachment at=null;
            while((at=boardAttachmentMapper(rset))!=null){
                aList.add(at);

            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return aList;


    }
    public List<Attachment> selectReplyAttachment(Connection conn, List<Reply> rList) {
        List<Attachment> aList=new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReplyAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            for (Reply r: rList) {
                if(r.getReplyType()== ReplyType.PICTURE){
                    psmt.setLong(1, r.getReplyNo());
                    rset = psmt.executeQuery();
                    r.setAt(replyAttachmentMapper(rset));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return aList;


    }
    public int insertBoardAttachment(Attachment at, Connection conn) {

        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertBoardAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, at.getBoardNo());
            psmt.setString(2, at.getOriginName());
            psmt.setString(3, at.getChangeName());
            psmt.setString(4, at.getFilePath());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }

        return result;


    }
 public int insertReplyAttachment(Attachment at, Connection conn) {

        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertReplyAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, at.getReplyNo());
            psmt.setString(2, at.getOriginName());
            psmt.setString(3, at.getChangeName());
            psmt.setString(4, at.getFilePath());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }

        return result;

    }

    public int updateInquireAttachment(Attachment at, Connection conn){
        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("updateInquireAttachment");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setString(1,at.getOriginName());
            psmt.setString(2,at.getChangeName());
            psmt.setString(3,at.getFilePath());
            psmt.setInt(4,at.getFileNo());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;
    }
    public int deleteAttachment(int fileNo, Connection conn){
        PreparedStatement psmt = null;

        String sql = prop.getProperty("deleteAttachment");
        int result=0;
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, fileNo);
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;

    }
    public int deleteBoardAttachment(int boardNo, Connection conn){
        PreparedStatement psmt = null;
        String sql = prop.getProperty("deleteBoardAttachment");
        int result=0;
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardNo);
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;

    }

    private Attachment replyAttachmentMapper(ResultSet rset) throws SQLException {
        Attachment at=null;
        if(rset.next()){
            at=Attachment.replyAttachment(rset.getInt("FILE_NO"),
                    rset.getInt("REPLY_NO"),
                    rset.getString("ORIGIN_NAME"),
                    rset.getString("CHANGE_NAME"),
                    rset.getString("FILE_PATH"),
                    rset.getDate("UPLOAD_DATE"));

        }
        return at;
    }
    private Attachment boardAttachmentMapper(ResultSet rset) throws SQLException {
        Attachment at=null;
        if(rset.next()){
            at=Attachment.fileAttachment(rset.getInt("FILE_NO"),
                    rset.getInt("BOARD_NO"),
                    rset.getString("ORIGIN_NAME"),
                    rset.getString("CHANGE_NAME"),
                    rset.getString("FILE_PATH"),
                    rset.getDate("UPLOAD_DATE"));
        }
        return at;
    }
    private Attachment groupUpAttachmentMapper(ResultSet rset) throws SQLException {
        Attachment at=null;
        if(rset.next()){
            at=Attachment.groupUpAttachment(rset.getInt("FILE_NO"),
                    rset.getString("ORIGIN_NAME"),
                    rset.getString("CHANGE_NAME"),
                    rset.getString("FILE_PATH"),
                    rset.getDate("UPLOAD_DATE"));
        }
        return at;
    }
    private Attachment thumbNailMapper(ResultSet rset)throws SQLException {
        Attachment at=null;
        if(rset.next()){
            at=Attachment.getThumbnailInstance(
                    rset.getInt("BOARD_NO"),
                    rset.getString("ORIGIN_NAME"),
                    rset.getString("BOARD_TITLE"),
                    rset.getString("CHANGE_NAME"),
                    rset.getString("FILE_PATH"));
        }
        return at;
    }

    public int selectFileNo(Connection conn){
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectFileNo");
        int fileNo=0;
        try {
            psmt = conn.prepareStatement(sql);
            rset=psmt.executeQuery();
            if (rset.next()) {
                fileNo=rset.getInt("NEXTVAL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return fileNo;
    }

    public int insertGradeAttachment(Attachment at, Connection conn) {

        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertGradeAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, at.getFileNo());
            psmt.setInt(2, at.getBoardNo());
            psmt.setString(3, at.getOriginName());
            psmt.setString(4, at.getChangeName());
            psmt.setString(5, at.getFilePath());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;

    }
    public void selectGradeUpAttachment(Connection conn, ArrayList<GradeUp> gList) {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            for (GradeUp g:gList) {
                psmt.setInt(1, g.getFileNo());
                rset = psmt.executeQuery();
                Attachment at = groupUpAttachmentMapper(rset) ;
                g.setAttachment(at);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
    }


    public List<Attachment> selectThumbNailList(Connection conn, BoardType boardType, int startNum,int endNum) {
        List<Attachment> tList=new ArrayList<>();
        PreparedStatement psmt=null;
        ResultSet rset=null;
        String sql=prop.getProperty("selectThumbnailList");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,boardType.typeNo);
            psmt.setInt(2,startNum);
            psmt.setInt(3,endNum);
            rset=psmt.executeQuery();
            Attachment at=null;
            while((at=thumbNailMapper(rset))!=null){
                tList.add(at);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }

        return tList;
    }

    public int deleteAttachmentList(List<Attachment> dList, Connection conn) {
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("deleteAttachment");
        try {
            psmt=conn.prepareStatement(sql);
            for (Attachment at:dList) {
                psmt.setInt(1,at.getFileNo());
                result=psmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }
}
