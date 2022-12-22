package com.example.QuizApp.data.quizes;

import com.example.QuizApp.data.exercises.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

import java.util.Optional;

import java.util.Set;


@Service
public class QuizService {

    private final QuizRepository repo;

    @Autowired
    public QuizService(QuizRepository repo) {
        this.repo = repo;
    }


    public List<Quiz> showAll()
    {
       return repo.findAll();
    }

    public Quiz insert(Quiz quiz) {
       return repo.save(quiz);
    }

    public List<Quiz> showByType(QuizType type)
    {
        return repo.findByType(type.name());
    }

    public Boolean deleteById(Long id) {
        repo.deleteById(id);
        return true;
    }

    public void deleteAll()
    {
        repo.deleteAll();
    }

    public List<Quiz> showByClass(int classID)
    {
         return repo.findByClassId(classID);
    }

    public Quiz showSafeByID(Long quizID)
    {
        Optional<Quiz> quiz = repo.findById(quizID);

        if(quiz.isPresent())
        {
            if(quiz.get() instanceof TeacherQuiz)
                ((TeacherQuiz) quiz.get()).hideTeacher();

            return quiz.get();
        }else
        {
            //TODO: throw error
            return null;
        }
    }
}
