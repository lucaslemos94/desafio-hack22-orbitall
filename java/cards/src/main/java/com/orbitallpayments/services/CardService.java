package com.orbitallpayments.hack22.services;
import com.orbitallpayments.hack22.domains.Card;
import com.orbitallpayments.hack22.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> findAll(){
        List<Card> cards = new ArrayList<>();
        for (Card card : (List<Card>) cardRepository.findAll()){
            cards.add(card);

        }
        return cards;
    }


    public Card save (Card card){
        return cardRepository.save(card);

    }

    public void deleteById(Long id){
        cardRepository.deleteById(id);
    }

    public Optional<Card> findById(Long id){

        return cardRepository.findById(id);

    }


}
