package com.restaurant.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChefService {

    private final ChefRepository chefRepository;

    @Autowired
    public ChefService(ChefRepository chefRepository){
        this.chefRepository = chefRepository;
    }

    public List<Chef> save (List<Chef> chefList){
        List<Chef> chefs = new ArrayList<>();
        if (chefList == null){
            return chefs;
        }
        for (Chef chef : chefList){
            chefs.add(chefRepository.save(chef));
        }
        return chefs;
    }
}

