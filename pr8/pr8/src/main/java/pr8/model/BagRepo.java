package pr8.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BagRepo extends JpaRepository<Bag, Integer> {
    String testQuery = """
            INSERT INTO bag (brand, material, type, cost, volume)
            VALUES
              ('AA', 'aa', 'aaaa', 100, 200.0),
              ('BB', 'bb', 'bbbb', 200, 250.0),
              ('CC', 'cc', 'cccc', 300, 300.0);
            """;
    String idQuery = """
            SELECT max(id) FROM bag;
            """;
    List<Bag> findByCostLessThanEqual(float minPrice);

    @Modifying
    @Query(value = testQuery, nativeQuery = true)
    void fillTest();


    @Query(value = idQuery, nativeQuery = true)
    int getMaxId();
}
