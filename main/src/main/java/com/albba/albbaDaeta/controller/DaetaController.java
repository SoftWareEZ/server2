package com.albba.albbaDaeta.controller;

import com.albba.albbaDaeta.dto.DaetaAcceptDto;
import com.albba.albbaDaeta.dto.DaetaRequestChkDto;
import com.albba.albbaDaeta.dto.DaetaRequestDto;
import com.albba.albbaDaeta.dto.DaetaViewDto;
import com.albba.albbaDaeta.repository.DaetaRepository;
import com.albba.albbaDaeta.repository.DaetaRequestRepository;
import com.albba.albbaDaeta.service.DaetaService;
import com.albba.albbaDaeta.table.Daeta;
import com.albba.albbaDaeta.table.DaetaRequest;
import com.albba.albbaUser.dto.LoginDto;
import com.albba.albbaUser.dto.TokenDto;
import com.albba.albbaUser.jwt.JwtFilter;
import com.albba.albbaUser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albba")
@RequiredArgsConstructor
public class DaetaController {
    private final DaetaService daetaService;
    private final DaetaRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final DaetaRepository daetaRepository;

    @PostMapping("/daeta/request")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void DaetaRequest(@RequestBody DaetaRequestDto requestDto) {

        DaetaRequest request = new DaetaRequest(requestDto.getDate(),requestDto.getStoreId(), requestDto.getRequestId(),
                userRepository.findByUserId(requestDto.getRequestId()).getRealname());
        daetaService.requestSave(request);

    }

    @PostMapping("/daeta/request/check")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public DaetaRequest Requestchk(@RequestBody DaetaRequestChkDto chkDto)

    {
        return requestRepository.findDaetaRequestByRequestIdAndDateAndStoreId(chkDto.getUserId(),chkDto.getDate(), chkDto.getStoreId());
    }

    @PostMapping("/daeta/accept/view")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Daeta> AcceptView(@RequestBody DaetaViewDto viewDto)
    {
        return daetaService.daetaView(viewDto.getDate(), viewDto.getStoreId());

    }



    @PostMapping("/daeta/accept")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void DaetaAccept(@RequestBody DaetaAcceptDto acceptDto)
    {
        //System.out.println(acceptDto.getAcceptId()+" "+acceptDto.getNo());
        daetaService.accept(acceptDto);

        requestRepository.deleteById(acceptDto.getNo());

    }

    @GetMapping("/daeta/request/list/{storeId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<DaetaRequest> requestList(@PathVariable Long storeId)
    {
        return requestRepository.findDaetaRequestByStoreId(storeId);

    }
    @GetMapping("/daeta/request/view/admin/{storeId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Daeta> requestAdminList(@PathVariable Long storeId)
    {
        return daetaRepository.findDaetasByStoreIdAndApproved(storeId,(long)0);

    }

    @GetMapping("/daeta/approved/{no}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void DaetaApprove(@PathVariable Long no)
    {
        Daeta daeta = daetaRepository.findById(no)
                .orElseThrow(() -> new NullPointerException("해당 no가 존재하지 않습니다."));
        daeta.setApproved((long)1);
        daetaRepository.save(daeta);
    }


}
