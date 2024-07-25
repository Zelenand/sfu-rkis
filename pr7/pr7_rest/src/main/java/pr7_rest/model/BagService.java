package pr7_rest.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)

public class BagService {

    private final BagRepo bagRepo;

    @Autowired
    public BagService(BagRepo bagRepo) {
        this.bagRepo = bagRepo;
    }
    public List<Bag> displayAllBags() {
        return bagRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Bag findById(int id) {
        return bagRepo.findById(id).orElse(null);
    }
    @Transactional
    public void update(long id, Bag bag) {
        bag.setId(id);
        bagRepo.save(bag);
    }
    @Transactional
    public void save(Bag bag) {
        bagRepo.save(bag);
    }
    @Transactional
    public void delete(int id) {
        bagRepo.deleteById(id);
    }

    public List<Bag> filterByCost(float minPrice) {
        return bagRepo.findByCostLessThanEqual(minPrice);
    }

    public int getLastId(){
        return bagRepo.getMaxId();
    }

    @Transactional
    public void addTest() {
        bagRepo.fillTest();
    }
}
