package crud.candidate.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

    private Integer id;

    private String name;

    private String taxId;

    private String email;

    private Date birthDate;

    private String intentedRole;

    private String curriculum;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public static Candidate fromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String taxId = resultSet.getString("tax_id");
        String email = resultSet.getString("email");
        Date birthDate = resultSet.getDate("birth_date");
        String intentedRole = resultSet.getString("intented_role");
        String curriculum = resultSet.getString("curriculum");
        LocalDateTime updatedAt = LocalDateTime.ofInstant(resultSet.getDate("updated_at").toInstant(), ZoneId.of("BET"));
        LocalDateTime createdAt = LocalDateTime.ofInstant(resultSet.getDate("created_at").toInstant(), ZoneId.of("BET"));

        return new Candidate(id, name, taxId, email, birthDate, intentedRole, curriculum, updatedAt, createdAt);
    }

}
