package tk.newsoulmate.domain.type;

import java.util.Arrays;

public enum BoardType {
    VOLUNTEER(1,"volunteerRev"),ADOPT(2,"adoptRev"),REPORT(3,"report"),QNA(4,"inquire"),FaQ(5,"FaQ"),NOTICE(6,"notice");
    public int typeNo;
    public String boardName;
    BoardType(int typeNo,String boardName){
        this.typeNo=typeNo;
        this.boardName=boardName;
    }

    public static BoardType valueOfNo(int typeNo){
        BoardType boardTypes [] = BoardType.values();
        return Arrays.asList(boardTypes).stream().filter(e->e.typeNo==typeNo).findAny().orElse(VOLUNTEER);
    }

    public static BoardType valueOfName(String boardName){
        BoardType boardTypes [] = BoardType.values();
        return Arrays.asList(boardTypes).stream().filter(e->e.boardName==boardName).findAny().orElse(VOLUNTEER);
    }

    }
