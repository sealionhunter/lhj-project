package service;

import org.springframework.validation.BindException;

import command.ScoreInputCommand;

public interface ScoreService {
    void inputScore(ScoreInputCommand cmd, BindException errors) throws Exception;
}
