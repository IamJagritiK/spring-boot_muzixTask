package com.stackroute.service;

import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;



        import com.stackroute.domain.Muzix;
        import com.stackroute.exceptions.TrackNotFoundException;
        import com.stackroute.repository.MuzixRepository;
        import org.springframework.beans.factory.annotation.Autowired;


        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Service
@Profile("dev")
public class TrackDummyImpl implements MuzixService {



    MuzixRepository muzixRepository;

    @Autowired
    public TrackDummyImpl(MuzixRepository muzixRepository){
        this.muzixRepository = muzixRepository;
        System.out.println("I am in the constructor");
    }
    @Override
    public Muzix saveTracks(Muzix muzix) {
        Muzix savedMuzix = muzixRepository.save(muzix);
        System.out.println("hello I am in save track");
        return savedMuzix;
    }

    @Override
    public List<Muzix> getAlltracks() {

        return muzixRepository.findAll();
    }

    @Override
    public Muzix getById(int id) throws TrackNotFoundException {
        Optional<Muzix> userId = muzixRepository.findById(id);
        if(userId.isEmpty()){
            throw new TrackNotFoundException("Track not found!");
        }
        return userId.get();
    }

    @Override
    public String deleteById(int id) throws TrackNotFoundException {
        Optional<Muzix> userId = muzixRepository.findById(id);
        if(userId.isEmpty()){
            throw new TrackNotFoundException("Track not found!");
        }
        muzixRepository.deleteById(id);
        return "done";
    }

    @Override
    public boolean updateById(Muzix musix, int id) throws TrackNotFoundException {
        Optional<Muzix> userOptional = muzixRepository.findById(id);

        if(userOptional.isEmpty()){
            throw new TrackNotFoundException("Track not found!");
        }

        musix.setId(id);

        muzixRepository.save(musix);
        return true;
    }

    @Override
    public boolean UpdateComments(int trackId, String trackComments) {
        Muzix user =  muzixRepository.getOne(trackId);
        user.setComments(trackComments);
        muzixRepository.save(user);
        return true;
    }


    @Override
    public List<Muzix> getByName(String name) {
        List<Muzix> userId = muzixRepository.findTitleByName(name);
        return userId;
    }



}
