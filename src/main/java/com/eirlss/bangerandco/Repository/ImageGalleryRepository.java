package com.eirlss.bangerandco.Repository;

import com.eirlss.bangerandco.Model.ImageGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageGalleryRepository extends JpaRepository<ImageGallery, Long>
{

}

