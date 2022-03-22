package com.encore.AI_Posturecoaching.lecturer.ui;


import com.encore.AI_Posturecoaching.lecturer.service.CoachingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoachingController {

    private final CoachingService coachingService;


}
