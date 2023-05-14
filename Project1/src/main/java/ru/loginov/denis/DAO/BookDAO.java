package ru.loginov.denis.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.loginov.denis.models.Book;
import ru.loginov.denis.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id =?", new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book saveBook){
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?,?,?)", saveBook.getTitle(), saveBook.getAuthor(), saveBook.getYear());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id =?",id);
    }

    public void update(int id, Book bookUpdate){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=? ",bookUpdate.getTitle(),bookUpdate.getAuthor(),bookUpdate.getYear(),id);
    }

    public Optional<Person> showBookOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.user_id = Person.id " +
                "WHERE Book.id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release (int id){
        jdbcTemplate.update("UPDATE Book SET user_id=NULL WHERE id =?", id);
    }
    public void assign(int id, Person selectPerson){
        jdbcTemplate.update("UPDATE Book SET user_id=? WHERE id =?",selectPerson.getId(), id);
    }


}
