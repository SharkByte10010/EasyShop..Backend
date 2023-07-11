package org.yearup.data.mysql;
// //
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }


    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>(); // Create a new instance of an ArrayList

        // SQL query to select all columns from the "categories" table
        String sql = """ 
                SELECT * FROM categories
                """;
        try {
            Connection connection = getConnection();// Creates the database connection
            PreparedStatement s = connection.prepareStatement(sql); // Creates a prepared statement with the sql query
            ResultSet row = s.executeQuery(); // Executes the query and gets a result set
            while (row.next()) {
                Category category = mapRow(row); // Converts the current row data to a Category object
                categories.add(category); // Adds the Category object to the list
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // In case of sql error throw RuntineException
        }
        return categories; // Return the list of categories
    }




    @Override
    public Category getById(int categoryId) {
        // get category by id
        String sql = "SELECT * FROM categories WHERE category_id = ?";

        try ( Connection connection = getConnection();
             PreparedStatement statement = getConnection().prepareStatement(sql)){

            statement.setInt(1,categoryId); // Set the parameter value in the sql statement
            ResultSet row = statement.executeQuery();
            if (row.next()){
                return mapRow(row);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Category create(Category category)
    {
        String sql = "INSERT INTO categories(name, description) " +
                " VALUES (?, ?);";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  // Prepare the sql statement with option to return generated keys
            statement.setString(1, category.getName());// Set the value for the first parameter in the sql statement
            statement.setString(2, category.getDescription());  // Set the value for the second parameter in the SQL statement

            int rowsAffected = statement.executeUpdate();// Execute the SQL statement and get the number of affected rows


            if (rowsAffected > 0) {

                ResultSet generatedKeys = statement.getGeneratedKeys();// If the insertion was successful retrieve the generated keys

                if (generatedKeys.next()) {

                    int categoryId = generatedKeys.getInt(1);// If there are generated keys retrieve the auto-incremented ID

                    // set the newly inserted category
                    category.setCategoryId(categoryId);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e); // In case of sql error throw a RuntimeException
        }
        return category;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        String sql = "UPDATE categories" +
                " SET name = ? " +
                "   , description = ?; ";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());


            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int categoryId)
    {
        String sql = "DELETE FROM categories WHERE category_id = ?";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }



    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
