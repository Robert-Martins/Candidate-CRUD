package crud.candidate.infrastructure.repositories;

import crud.candidate.domain.models.Candidate;
import crud.candidate.domain.repositories.ICandidateRepository;
import crud.candidate.infrastructure.configs.db.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandidateRepository implements ICandidateRepository {

    private static ICandidateRepository candidateRepository;

    @Override
    public void create(Candidate candidate) {
        String sql = "INSERT INTO candidate (name, tax_id, email, birth_date, intended_role, curriculum, updated_at, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setString(2, candidate.getTaxId());
            preparedStatement.setString(3, candidate.getEmail());
            preparedStatement.setDate(4, new Date(candidate.getBirthDate().getTime()));
            preparedStatement.setString(5, candidate.getIntentedRole());
            preparedStatement.setString(6, candidate.getCurriculum());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Candidate> findById(Integer id) {
        String sql = "SELECT * FROM candidate WHERE id = ?";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(Candidate.fromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM candidate";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                candidates.add(Candidate.fromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    @Override
    public void update(Candidate candidate) {
        String sql = "UPDATE candidate SET name = ?, tax_id = ?, email = ?, birth_date = ?, " +
                "intended_role = ?, curriculum = ?, updated_at = ? WHERE id = ?";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setString(2, candidate.getTaxId());
            preparedStatement.setString(3, candidate.getEmail());
            preparedStatement.setDate(4, new Date(candidate.getBirthDate().getTime()));
            preparedStatement.setString(5, candidate.getIntentedRole());
            preparedStatement.setString(6, candidate.getCurriculum());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setInt(8, candidate.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM candidate WHERE id = ?";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean existsById(Integer id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM candidate WHERE id = ?)";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean existsByTaxId(String taxId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM candidate WHERE tax_id = ?)";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, taxId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean existsByEmail(String email) {
        String sql = "SELECT EXISTS(SELECT 1 FROM candidate WHERE email = ?)";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean existsByIdAndTaxId(Integer id, String taxId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM candidate WHERE id = ? AND tax_id = ?)";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, taxId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean existsByIdAndEmail(Integer id, String email) {
        String sql = "SELECT EXISTS(SELECT 1 FROM candidate WHERE id = ? AND email = ?)";

        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ICandidateRepository getInstance() {
        candidateRepository = Optional.ofNullable(candidateRepository).isEmpty() ? new CandidateRepository() : candidateRepository;
        return candidateRepository;
    }

}
