package sample;

import java.util.Arrays;

/**
 * Created by Admin on 21.11.2016.
 */
public class Functions {

    public static String strin = new String("");//исскуственный буфер обмена для строки

    //Функция читает  исскуственный буффер (работает вместе с функциями для примененем правила)
    public static String getBufString (){
        return strin;
    }

    //Функция записывает в исскуственный буффер (работает вместе с функциями для примененем правила)
    public static void setBufString (String f){
        strin = f;

    }

    //проверяет корректна ли секвенция
    public static boolean SequenceCorrect (String seq){
        int count = 0;//для проверки

        if(!(seq.contains("+"))) return false; //если нет знака секвенции
        if(seq.indexOf("+")!=seq.lastIndexOf("+"))return false;//если более 2-ух знаков секвенции

        //проверка использованых символов
        for(int i = 0; i<seq.length();i++){
            count = seq.charAt(i);
            if(!((count>=97)&&(122>=count))&&('+'!=count)&&('('!=count)&&(')'!=count)&&('='!=count)&&('-'!=count)&&('&'!=count)&&(','!=count))return false;

            else if (((count>=97)&&(122>=count))&&(i+1<seq.length())&&(count!='v')){
                count = seq.charAt(i+1);
                if(count=='-')return false;
                if(((count>=97)&&(122>=count))&&(count!='v'))return false;//(count>=97)&&(122>=count)&&(i+1<seq.length())
            }
        }

        //проверка отсутствия запятых после +..,.,., или ... ,+ или , ... +
        if(seq.contains(",")){
            if((seq.indexOf("+")!=0)&&(','==seq.charAt(seq.indexOf("+")-1)))return false;
            if((seq.indexOf(",")==0))return false;
            for(int i = 0; i<seq.indexOf("+");i++){
                if(i>0&&seq.charAt(i)==','){
                    if(seq.charAt(i-1)==','||seq.charAt(i-1)=='(')return false;
                    if(i<seq.indexOf("+")-1 && seq.charAt(i+1)==')')return false;
                }
            }
            String S = seq.substring(seq.indexOf("+"));
            if(S.contains(","))return false;
        }

        count = 0;
        //проверка скобок
        if (seq.contains("(")||seq.contains(")")){
            if (seq.contains("(")&&seq.contains(")")){
                if((seq.indexOf('(')>seq.indexOf(')'))||(seq.lastIndexOf('(')>seq.lastIndexOf(')'))) return false;
                for(int i = 0; i<seq.length();i++){
                    if('('==seq.charAt(i)){
                        if (seq.charAt(i+1)==')')return false; //скобки не пустые
                        count++;
                    }
                    if(')'==seq.charAt(i))
                        count--;
                    if (count<0)return false;
                }
                if(count!=0)return false;
            }
            else return false;}

        //проверка, что после каждого оператора отрицания идет переменная или (),
        if(seq.contains("-")){
            int indexCH = 0; //индекс символа следующего за -

            for(int i = 0; i<seq.length();i++){
                if('-'==seq.charAt(i)){
                    if(i==seq.length()-1)return false;
                    else{
                        indexCH = seq.charAt(i+1);
                        if(('='==indexCH)||('v'==indexCH)||('+'==indexCH)||('&'==indexCH)||(','==indexCH))return false;
                    }
                }
            }
        }
        //проверка на |- a&v=>b операторы не могут стоять подряд кроме => -a
        if(seq.contains("=")||seq.contains("v")||seq.contains("&")){
            if(seq.indexOf("+")!=seq.length()-1)
                count = seq.charAt(seq.indexOf("+")+1);
            if(count=='='||count=='&'||count=='v') return false;
            int indexCH = 0; //индекс символа следующего ...
            for(int i = 0; i<seq.length();i++){
                if(('='==seq.charAt(i))||('v'==seq.charAt(i))||('&'==seq.charAt(i))){
                    if((i==seq.length()-1)||(i==0))return false;
                    else if(seq.charAt(i-1)==',')return false;
                    else{
                        indexCH = seq.charAt(i+1);
                        if(('='==indexCH)||('v'==indexCH)||('+'==indexCH)||('&'==indexCH)||(','==indexCH))return false;
                    }
                }
            }
        }
        return true;
    }

    //удаляет лишние скобки
    public static String delitSCOB(String seq){
        if(seq.contains("(")&&(seq.indexOf("(")==0&&seq.lastIndexOf(")")==seq.length()-1))
            if(SequenceCorrect("+"+seq.substring(1, seq.length()-1))) seq = seq.substring(1, seq.length()-1);
        return seq;
    }

    // проверяет что все V или = или & взяты в ()
    public static boolean ScobkaVimpI(String seq){
        if(!(seq.contains("v")||seq.contains("=")||seq.contains("&")))return true;
        int count = 0;
        for(int i = 0; i < seq.length();i++){
            switch(seq.charAt(i)){
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
                case 'v':
                case '=':
                case '&':
                    if(count==0)return false;
            }
        }
        return true;
    }

    // проверяет что все V или = взяты в ()
    public static boolean ScobkaVimp(String seq){
        if(!(seq.contains("v")||seq.contains("=")))return true;
        int count = 0;
        for(int i = 0; i < seq.length();i++){
            switch(seq.charAt(i)){
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
                case 'v':
                case '=':
                    if(count==0)return false;
            }
        }
        return true;
    }

    // проверяет что все = взяты в ()
    public static boolean Scobkaimp(String seq){
        if(!(seq.contains("=")))return true;
        int count = 0;
        for(int i = 0; i < seq.length();i++){
            switch(seq.charAt(i)){
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
                case '=':
                    if(count==0)return false;
            }
        }
        return true;
    }

    public static String Sequence_1 (int x, String seq){
        if(!(SequenceCorrect (seq)))return seq;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        int count = 0;
        int diz = -1; //индекс первой в строке &
        String Lev = seq.substring(0, s);
        String Prav = seq.substring(s+1); //подстрока, правое выражение от знака секвенции
        if(!Prav.contains("&"))return seq; //если нет знака &

        if(!ScobkaVimp(Prav)) return seq;//если v или = не в скобках

        if(Prav.contains("(")){
            count = 0;
            for(int i = 0; i<Prav.length();i++){
                switch (Prav.charAt(i)) {
                    case '(':
                        count++;
                        break;
                    case ')':
                        count--;
                        break;
                    case '&':
                        if(count==0){
                            diz=i;
                            i=Prav.length();
                        } //выйти, если зашло в эту ветку *Next2
                        break;
                }

            }
            if(diz<0)return seq;
        }
        else diz = Prav.indexOf("&");
        Prav = seq.substring(s+1);
        String dizL = Prav.substring(0, diz); //подстрока слева от &
        String dizP = Prav.substring(diz+1);//подстрока справа от &
        if(x==2) {
            dizP = delitSCOB(dizP);
            return Lev+"+"+dizP;
        }
        else {
            dizL = delitSCOB(dizL);
            return Lev+"+"+dizL;
        }
    }

    //требует перед вызовом заполнить буффер необхадимым значением
    public static String Sequence_2_3 (int x, String seq){
        if(!(SequenceCorrect (seq)))return seq;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        String Lev = seq.substring(0, s);//все что слева от секвенции
        String Prav = seq.substring(s+1); //подстрока, правое выражение от знака секвенции
        String buf = new String(getBufString());//строка из искуственного буффера
        //проверяем значение в искуственном буффере
        if(!(SequenceCorrect ("+"+buf)))return seq;//проверяем коректность данных в буфере
        if(!ScobkaVimp(buf))buf = "("+buf+")"; //проверка буффера**********
        if(!ScobkaVimp(Prav))Prav = "("+Prav+")"; //*
        if(x==2)return Lev+"+"+Prav+"&"+buf; //2
        else return Lev+"+"+buf+"&"+Prav; //3
    }

    public static String Sequence_4_5 (int x, String seq){
        if(!(SequenceCorrect (seq)))return seq;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        String Lev = seq.substring(0, s);
        String Prav = seq.substring(s+1); //подстрока, правое выражение от знака секвенции
        if(!Prav.contains("v"))return seq; //если нет знака v
        int diz = Prav.indexOf("v"); //индекс первой в строке v
        if(!Scobkaimp(Prav))return seq;
        if(seq.contains("(")){
            diz = -1;
            int count = 0;
            for(int i = 0; i<Prav.length();i++){
                switch (Prav.charAt(i)) {
                    case '(':
                        count++;
                        break;
                    case ')':
                        count--;

                        break;
                    case 'v':
                        if(count==0) diz = i;

                        break;
                }

            }
        }
        if(diz<0)return seq;
        String dizL = Prav.substring(0, diz); //подстрока слева от v
        String dizP = Prav.substring(diz+1);//подстрока справа от v
        if(x==5) {
            dizP = delitSCOB(dizP);
            return Lev+"+"+dizP;
        }
        else {
            dizL = delitSCOB(dizL);
            return Lev+"+"+dizL;}
    }

    //требует перед вызовом заполнить буффер необхадимым значением
    public static String Sequence_6 (int x, String seq){
        if(!(SequenceCorrect (seq)))return seq;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        String Lev = seq.substring(0, s);
        String Prav = seq.substring(s+1); //подстрока, правое выражение от знака секвенции
        //проверяем значение в искуственном буффере
        String buf = new String(getBufString());//строка из искуственного буффера
        if(!Scobkaimp(buf))return seq;
        if(!(SequenceCorrect ("+"+buf))||!(buf.contains("v")))return seq;//проверяем коректность данных в буфере
        int indexV = buf.indexOf("v");//индекс v
        if(buf.contains("(")){
            indexV = -1;
            int count = 0;
            for(int i = 0; i<buf.length();i++){
                switch (buf.charAt(i)) {
                    case '(':
                        count++;
                        break;
                    case ')':
                        count--;

                        break;
                    case 'v':
                        if(count==0)indexV = i;

                        break;
                }}}
        if(indexV<0)return seq;
        if(x==1){
            String levV = delitSCOB(buf.substring(0, indexV));
            if(Lev.length()==0) return levV+"+"+Prav;
            else return Lev+","+levV+"+"+Prav;
        }
        else if(x==2){
            String pravV = delitSCOB(buf.substring(indexV+1));
            if(Lev.length()==0) return pravV+"+"+Prav;
            else return Lev+","+pravV+"+"+Prav;
        }
        else return Lev+"+"+buf;
    }

    //Преобразовывет по правилу 7 введение => (=)
    public static String Sequence_7 (String seq){
        if(!(SequenceCorrect (seq)))return seq;
        String Lev = seq.substring(0, seq.indexOf("+"));
        String Prav = seq.substring(seq.indexOf("+")+1);
        if(!Prav.contains("="))return seq; //если нет знака =>
        int diz = Prav.indexOf("=");//индекс =>
        int count = 0;

        if(Prav.contains("(")){
            diz = -1;
            count = 0;
            Next2:for(int i = 0; i<Prav.length();i++){
                switch (Prav.charAt(i)) {
                    case '(':
                        count++;
                        break;
                    case ')':
                        count--;

                        break;
                    case '=':
                        if(count==0) {
                            diz = i;
                            i=Prav.length();
                            continue Next2;
                        }

                        break;
                }

            }

        }
        if(diz<0)return seq;
        String dizL = Prav.substring(0,diz);
        String dizP = Prav.substring(diz+1);
        dizL = delitSCOB(dizL);
        if(Lev.length()==0)return dizL+"+"+dizP;
        else return Lev+","+dizL+"+"+dizP;
    }

    //требует перед вызовом заполнить буффер необхадимым значением
    public static String Sequence_8 (int x, String seq){
        if(!(SequenceCorrect (seq)))return seq;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        String Lev = seq.substring(0, s);//все что слева от секвенции
        String Prav = seq.substring(s+1); //подстрока, правое выражение от знака секвенции
        if(Prav.length()==0)return seq;//если справа ничего
        //проверяем значение в искуственном буффере
        String buf = new String(getBufString());//строка из искуственного буффера
        if(!(SequenceCorrect ("+"+buf)))return seq;//проверяем коректность данных в буфере
        buf = delitSCOB(buf);
        if(x==1){
            if(!Scobkaimp(buf)) buf = "("+buf+")";
            if(!Scobkaimp(Prav)) Prav = "("+Prav+")";
            return Lev+"+"+buf+"="+Prav;
        }
        return Lev+"+"+buf;
    }

    //Преобразовывет по правилу 9 удаление -
    public static String Sequence_9 (String seq){
        if(!(SequenceCorrect (seq)))return seq;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        String Lev = seq.substring(0, s);//все что слева от секвенции
        String Prav = seq.substring(s+1); //подстрока, правое выражение от знака секвенции
        if(Prav.length() == 0) return seq;
        if(!ScobkaVimpI(Prav))Prav = "("+Prav+")";
        if (Lev.length()==0)return "-"+Prav+"+";
        return Lev+","+"-"+Prav+"+";
    }

    //требует перед вызовом заполнить буффер необхадимым значением
    public static String Sequence_10 (int x, String seq){
        if(!(SequenceCorrect (seq)))return seq;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        String Lev = seq.substring(0, s);//все что слева от секвенции
        String Prav = seq.substring(s+1);//подстрока, правое выражение от знака секвенции
        if(Prav.length() != 0)return seq;
        String buf = new String(getBufString());//строка из искуственного буффера
        if(!(SequenceCorrect ("+"+buf)))return seq;//проверяем коректность данных в буфере
        if(x==2) {
            if(!ScobkaVimpI(buf))buf = "("+buf+")"; //проверка буффера
            return Lev+"+"+"-"+buf;
        }
        else {
            buf = delitSCOB(buf);
            return Lev+"+"+buf;
        }
    }

    //требует перед вызовом заполнить буффер необхадимым значением (функцией, которая будет стоять слева) / последовательность
    public static String Sequence_11 (String seq){
        if(!(SequenceCorrect (seq)))return seq;
        boolean bom = false;
        String buf = new String(getBufString());//строка из искуственного буффера
        if(!(SequenceCorrect (buf+"+")))return seq;//проверяем коректность данных в буфере
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        String Lev = seq.substring(0, s);//все что слева от секвенции
        String Prav = seq.substring(s+1); //подстрока, правое выражение от знака секвенции
        if(!Lev.contains(",")) return seq;
        String copyBuf[] = buf.split(","); //создаем массив формул из buf
        Arrays.sort(copyBuf);//сортируем массив
        String copyLev[] = Lev.split(","); //создаем массив формул из левой части секвенции
        Arrays.sort(copyLev);//сортируем массив
        if(Arrays.equals(copyLev,copyBuf)) return buf + "+" + Prav;
        return seq;
    }

    //Преобразовывет по правилу 12 лишней посылки
    public static String Sequence_12 (String seq){
        if(!(SequenceCorrect (seq)))return seq;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        String Prav = seq.substring(s+1); //подстрока, правое выражение от знака секвенции
        String Lev = seq.substring(0, s);//все что слева от секвенции
        if(Lev.contains(",")){
            int zap = Lev.lastIndexOf(",");//первая запятая слева от +
            Lev = Lev.substring(0, zap);
            return Lev+"+"+Prav;}
        else return "+"+Prav;
    }

    //метод, который проверяет является ли секвенция аксиомой *
    public static boolean SequenceAxioma (String seq){
        if(!(SequenceCorrect(seq)))return false;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        if(seq.length()==1)return false;
        if (seq.substring(0, s)==""||seq.substring(s+1)=="")return false;//*
        else if (delitSCOB(seq.substring(0, s)).equals(delitSCOB(seq.substring(s+1)))) return true;
        return false;
    }

    //метод, проверяющий доказуемость секвенции *
    public static boolean SequenceDoc (String seq){
        if(!SequenceCorrect (seq))return false;
        int s = seq.indexOf("+"); //индекс знака секвенции в строке
        if (seq.substring(0, s)==""||seq.substring(s+1)=="")return false;
        seq=seq.replace('+', '=');
        int count;
        String p = "";//создаем строку переменных
        //заполняем строку переменных
        for(int z=0;z<seq.length();z++ ){
            count = seq.charAt(z);
            if((((count>=97)&&(122>=count))&&(count!='v'))&&!p.contains(""+seq.charAt(z))){
                p = p+seq.charAt(z);//если символ переменная и его нет в строке переменных - добавляем
            }
        }
        char copyZ = ' ';
        String copySeq = seq;
        int a = 0;
        // p.lenght() - кол-во переменных; Math.pow(2,p.length())- таблица всех возможных значений переменных
        for(byte i = 0; i<Math.pow(2,p.length());i++){
            //заменяем текущими константами переменные

            copySeq = seq;
            for(int n=0;n<p.length();n++){
                // n номер проверяемого бита
                a = i;
                a = (1 << n) &  a;
                //System.out.println("a === " + a);
                if (a != 0 )
                {
                    copyZ = '1';// бит №n установлен в единицу
                }
                else
                {
                    copyZ = '0';// бит №n нулевой
                }
                //System.out.println("p.charAt(n) === " + p.charAt(n));
                //System.out.println("copyZ === " + copyZ);
                copySeq = copySeq.replace(p.charAt(n), copyZ);
                //System.out.println("copySeq === " + copySeq);
            }
            //System.out.println(copySeq + " =============================================== win ");
            //по приоретету три цикла находим операнд, смотрим лево (и право), заменяем константой

            if(copySeq.contains("(")){
                String SS = "";//подстрока в самых главных скобках
                int Sbegin = 0;
                int Send = 0;
                for(int z=0;z<copySeq.length()||copySeq.contains("(");z++ ){
                    count = copySeq.charAt(z);
                    if(count == '('){
                        Sbegin = z;
                    }
                    if(count == ')'){
                        Send = z;
                        SS = copySeq.substring(Sbegin+1, Send);
                        SS = VarLog (SS);
                        copySeq = copySeq.substring(0,Sbegin)+SS+copySeq.substring(Send+1);//?
                        z = 0;
                        Send = 0;
                        Sbegin = 0;
                    }
                }
            }
            if(!copySeq.contains("(")){
                copySeq = VarLog (copySeq);
            }
            if(copySeq.charAt(0)=='0')return false;
            //System.out.println("copySeq === " + copySeq);
        }
        return true;
    }

    //использовать для решения выражения с константами
    public static String VarLog (String copySeq){
        int count = 0;
        if(copySeq.contains("-")){
            do{
                for(int z=1;z<copySeq.length();z++ ){
                    count = copySeq.charAt(z);
                    if((count == '1' || count == '0')&&(copySeq.charAt(z-1)=='-')){
                        if(count == '1'){
                            copySeq = copySeq.substring(0, z-1)+"0"+copySeq.substring(z+1);
                        }
                        else{
                            copySeq = copySeq.substring(0, z-1)+"1"+copySeq.substring(z+1);
                        }

                    }
                }}
            while(copySeq.contains("-"));
        }
        count = 0;
        for(int z=1;z<copySeq.length();z++ ){
            count = copySeq.charAt(z);
            if(count == '&'){
                if(copySeq.charAt(z-1) == '1' && copySeq.charAt(z+1) == '1'){
                    copySeq = copySeq.substring(0, z-1)+"1"+copySeq.substring(z+2);
                    z -= 1;//*
                }
                else{
                    copySeq = copySeq.substring(0, z-1)+"0"+copySeq.substring(z+2);
                    z -= 1;//*
                }

            }
        }
        for(int z=1;z<copySeq.length();z++ ){
            count = copySeq.charAt(z);
            if(count == 'v'){
                if(copySeq.charAt(z-1) == '1' || copySeq.charAt(z+1) == '1'){
                    copySeq = copySeq.substring(0, z-1)+"1"+copySeq.substring(z+2);
                    z -= 1; //*
                }
                else{
                    copySeq = copySeq.substring(0, z-1)+"0"+copySeq.substring(z+2);
                    z -= 1; //*
                }

            }
        }
        for(int z=1;z<copySeq.length();z++ ){
            count = copySeq.charAt(z);
            if(count == '='){
                if(copySeq.charAt(z-1) == '1' && copySeq.charAt(z+1) == '0'){
                    copySeq = copySeq.substring(0, z-1)+"0"+copySeq.substring(z+2);
                    z -= 1; //*
                }
                else{
                    copySeq = copySeq.substring(0, z-1)+"1"+copySeq.substring(z+2);
                    z -= 1; //*
                }

            }
        }
        return copySeq;
    }


    //ВНИМАНИЕ!!! нужно запретить пользователю вводить русские символы, проверка работает только для строчной латиницы
    //если метод который преобразовывал секвенцию возвращает введенную строку, значит были некоректные данные, некоректный ввод!!!

    public static void main(String[] args) {
        boolean answer = SequenceDoc ("h=-(f=r)+k");
        System.out.println(answer);
        answer = SequenceDoc ("k+(k)");
        System.out.println(answer);
        /*
//        String seq = "(a&e=o)+(b)v(c)";
//        seq = delitSCOB(seq);
//        System.out.println(seq);
        System.out.println(SequenceAxioma("(a)+(a)"));
        System.out.println(SequenceAxioma("a+(a)"));
        System.out.println(SequenceAxioma("(a)+a"));
        System.out.println(SequenceAxioma("a+a"));
        System.out.println(SequenceAxioma("a+-a"));
        //1
        System.out.println("1");
        String str = new String("+(a&b)&(c&d)");
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        String gh = Sequence_1 (1,str);
        System.out.println(gh);
        gh = Sequence_1 (2,str);
        System.out.println(gh);
        System.out.println("");
        //2,3
        System.out.println("2, 3");
        str = new String("a,f,g,s+a");
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        setBufString("-f=n");
        gh = Sequence_2_3 (2,str);
        System.out.println(gh);
        gh = Sequence_2_3 (3,str);
        System.out.println(gh);
        System.out.println("");
        //4,5
        System.out.println("4, 5");
        str = "+(avb)vb";
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        gh = Sequence_4_5 (4,str);
        System.out.println(gh);
        gh = Sequence_4_5 (5,str);
        System.out.println(gh);
        System.out.println("");
        //6
        System.out.println("6");
        str = "+m";
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        setBufString("(avj)v(fvr)");
        gh = Sequence_6 (1,str);
        System.out.println(gh);
        gh = Sequence_6 (2,str);
        System.out.println(gh);
        gh = Sequence_6 (3,str);
        System.out.println(gh);
        System.out.println("");
        //7
        System.out.println("7");
        str = "a,g+(fvy)=a=b";
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        gh = Sequence_7 (str);
        System.out.println(gh);
        System.out.println("");

        //8
        System.out.println("8");
        str = "+m=b";
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        setBufString("(avj)");
        gh = Sequence_8 (1,str);
        System.out.println(gh);
        gh = Sequence_8 (2,str);
        System.out.println(gh);
        System.out.println("");

        //9
        System.out.println("9");
        str = "a,f+(mvg)";
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        gh = Sequence_9 (str);
        System.out.println(gh);
        System.out.println("");

        //10
        System.out.println("10");
        str = "f+";
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        setBufString("(-svg&j)");
        gh = Sequence_10 (1,str);
        System.out.println(gh);
        gh = Sequence_10 (2,str);
        System.out.println(gh);
        System.out.println("");

        //11
        System.out.println("11");
        str = "m,f+";
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        setBufString("f,m");
        gh = Sequence_11 (str);
        System.out.println(gh);
        System.out.println("");

        //12
        System.out.println("12");
        str = "gvf+";
        System.out.println(str);
        System.out.println(SequenceCorrect(str));
        gh = Sequence_12 (str);
        System.out.println(gh);
        System.out.println("");
        */
    }

}
