package crud.candidate.application.controllers;

import crud.candidate.application.dtos.CandidateDto;
import crud.candidate.application.services.ICandidateService;
import crud.candidate.domain.services.CandidateService;
import crud.candidate.domain.utils.HttpUtils;
import crud.candidate.domain.utils.JsonUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class CandidateController extends HttpServlet {

    private ICandidateService candidateService = CandidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"))) {
            this.candidateService.create(JsonUtils.fromJson(reader, CandidateDto.class));
            HttpUtils.setCreatedResponse(resp, "Candidato criado com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Optional.ofNullable(req.getParameter("id"))
                .ifPresentOrElse(
                        id -> this.findById(Integer.parseInt(id), resp),
                        () -> this.findAll(resp)
                );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"))) {
            this.candidateService.update(JsonUtils.fromJson(reader, CandidateDto.class));
            HttpUtils.setCreatedResponse(resp, "Candidato atualizado com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Optional.ofNullable(req.getParameter("id"))
                .ifPresent(
                        id -> this.deleteById(Integer.parseInt(id), resp)
                );
    }

    private void findById(Integer id, HttpServletResponse resp) {
        try {
            HttpUtils.setOkResponse(resp, this.candidateService.findById(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findAll(HttpServletResponse resp) {
        try {
            HttpUtils.setOkResponse(resp, this.candidateService.findAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteById(Integer id, HttpServletResponse resp) {
        try {
            this.candidateService.deleteById(id);
            HttpUtils.setOkResponse(resp, "Candidato deletado com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
