package org.jdbc_example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    // Выполнение запросов может приводить к исключениям,
    // поэтому нужно указывать базовое исключение
    public static void main(String[] args) throws SQLException {
        // Создаем соединение с базой в памяти.
        // Такая база создается прямо во время выполнения этой строчки
        // hexlet_test - Имя базы данных
        var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test");

        var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
        // Запрос выполняется через создание объекта стейтмента
        var statement = conn.createStatement();
        statement.execute(sql);
        statement.close(); // в конце закрывается

        var sql2 = "INSERT INTO users (username, phone) VALUES ('tommy', '123456789')";
        var statement2 = conn.createStatement();
        statement2.executeUpdate(sql2);
        statement2.close();

        var sql3 = "SELECT * FROM users";
        var statement3 = conn.createStatement();
        // Указатель на набор данных в памяти СУБД
        var resultSet = statement3.executeQuery(sql3);
        // Набор данных представляет из себя итератор
        // Перемещаясь по нему с помощью next() мы каждый раз получаем новые значения
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("phone"));
        }
        statement3.close();

        // Соединение нужно закрыть
        conn.close();
    }
}