package rs.ac.uns.ftn.isaprojekat.service.map;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;

import java.util.Set;

@Service
public class BoatServiceMap extends AbstractMapService<Boat, Long> implements BoatService {
    @Override
    public Set<Boat> findAll() {
        return super.findAll();
    }

    @Override
    public Boat findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Boat save(Long id, Boat object) {
        return super.save(id, object);
    }
}
