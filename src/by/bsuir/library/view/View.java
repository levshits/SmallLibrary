package by.bsuir.library.view;

import by.bsuir.library.bean.ParameterType;
import by.bsuir.library.bean.Request;
import by.bsuir.library.bean.Response;
import by.bsuir.library.controller.ActionType;
import by.bsuir.library.controller.Controller;
import by.bsuir.library.dao.SourceType;
import by.bsuir.library.entity.Book;
import by.bsuir.library.entity.BookGenre;

import java.sql.Date;
import java.util.List;

/**
 * Created by Valentin on 29.04.2015.
 */
public class View {
    private Controller controller;
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void addBookToDb(int id, String title, String ISBN, BookGenre genre,
                            int year, String language, String firstName,
                            String secondName, Date born, Date dead){
        Request request = new Request();
        request.setParam(ParameterType.ID, id);
        request.setParam(ParameterType.ISBN, ISBN);
        request.setParam(ParameterType.YEAR, year);
        request.setParam(ParameterType.LANGUAGE, language);
        request.setParam(ParameterType.GENRE, genre);
        request.setParam(ParameterType.TITLE, title);
        request.setParam(ParameterType.FIRST_NAME, firstName);
        request.setParam(ParameterType.SECOND_NAME, secondName);
        request.setParam(ParameterType.BORN, born);
        request.setParam(ParameterType.DEAD, dead);
        request.setParam(ParameterType.SOURCE_TYPE, SourceType.DB);
        request.setActionType(ActionType.ADD);
        Response response = controller.execute(request);
        if(response!=null && response.getsStatus()){
            System.out.println("Book was added");
        }else {
            System.out.println("Something wrong");
            System.out.println(response.getExceptionMessage());
        }

    }
    public void addBookToXml(int id, String title, String ISBN, BookGenre genre,
                             int year, String language, String firstName,
                             String secondName, Date born, Date dead){
        Request request = new Request();
        request.setParam(ParameterType.ID, id);
        request.setParam(ParameterType.ISBN, ISBN);
        request.setParam(ParameterType.YEAR, year);
        request.setParam(ParameterType.LANGUAGE, language);
        request.setParam(ParameterType.GENRE, genre);
        request.setParam(ParameterType.TITLE, title);
        request.setParam(ParameterType.FIRST_NAME, firstName);
        request.setParam(ParameterType.SECOND_NAME, secondName);
        request.setParam(ParameterType.BORN, born);
        request.setParam(ParameterType.DEAD, dead);
        request.setParam(ParameterType.SOURCE_TYPE, SourceType.XML);
        request.setActionType(ActionType.ADD);
        Response response = controller.execute(request);
        if(response!=null && response.getsStatus()){
            System.out.println("Book was added");
        }else {
            System.out.println("Something wrong");
            System.out.println(response.getExceptionMessage());
        }

    }
    public void removeFromDb(int id){
        Request request = new Request();
        request.setParam(ParameterType.ID, id);
        request.setParam(ParameterType.SOURCE_TYPE, SourceType.DB);
        request.setActionType(ActionType.REMOVE);
        Response response = controller.execute(request);
        if(response!=null && response.getsStatus()){
            System.out.println("Book was removed");
        }else {
            System.out.println("Something wrong");
            System.out.println(response.getExceptionMessage());
        }
    }
    public void removeFromXml(int id){
        Request request = new Request();
        request.setParam(ParameterType.ID, id);
        request.setParam(ParameterType.SOURCE_TYPE, SourceType.XML);
        request.setActionType(ActionType.REMOVE);
        Response response = controller.execute(request);
        if(response!=null && response.getsStatus()){
            System.out.println("Book was removed");
        }else {
            System.out.println("Something wrong");
            System.out.println(response.getExceptionMessage());
        }
    }
    public void getAllFromDb(){
        Request request = new Request();
        request.setParam(ParameterType.SOURCE_TYPE, SourceType.DB);
        request.setActionType(ActionType.GET_ALL);
        Response response = controller.execute(request);
        List<Book> books;
        if(response!=null && response.getsStatus() && response.getResult() instanceof List){
            books = (List<Book>)response.getResult();
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i).getFullDescription());
            }
            System.out.println("");
        }else {
            System.out.println("Something wrong");
            System.out.println(response.getExceptionMessage());
        }
    }
    public void getAllFromXml(){
        Request request = new Request();
        request.setParam(ParameterType.SOURCE_TYPE, SourceType.XML);
        request.setActionType(ActionType.GET_ALL);
        Response response = controller.execute(request);
        List<Book> books;
        if(response!=null && response.getsStatus() && response.getResult() instanceof List){
            books = (List<Book>)response.getResult();
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i).getFullDescription());
            }
            System.out.println("");
        }else {
            System.out.println("Something wrong");
            System.out.println(response.getExceptionMessage());
        }
    }
    public void writeCommonListToXml(String filename){
        Request request = new Request();
        request.setParam(ParameterType.FILENAME, filename);
        request.setActionType(ActionType.WRITE_TO_XML);
        Response response = controller.execute(request);
        if(response!=null && response.getsStatus() ){
            System.out.println("File created");
        }else {
            System.out.println("Something wrong");
            System.out.println(response.getExceptionMessage());
        }
    }
    public void importListFromXmlToDb(){
        Request request = new Request();
        request.setActionType(ActionType.IMRORT_TO_DB);
        Response response = controller.execute(request);
        if(response!=null && response.getsStatus() ){
            System.out.println("Import released");
        }else {
            System.out.println("Something wrong");
            System.out.println(response.getExceptionMessage());
        }
    }
}
