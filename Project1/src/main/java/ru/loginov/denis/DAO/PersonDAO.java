package ru.loginov.denis.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.loginov.denis.models.Book;
import ru.loginov.denis.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show (int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(name,year) VALUES (?,?)", person.getName(), person.getYear());
    }

    public void delete(int id){
    jdbcTemplate.update("delete from person where id=?",id);
    }

    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET name=?, year=? WHERE id=?", updatePerson.getName(), updatePerson.getYear(), id);

    }

    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE user_id=?",new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));

    }


}
