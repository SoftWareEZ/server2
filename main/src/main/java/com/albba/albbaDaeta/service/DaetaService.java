package com.albba.albbaDaeta.service;


import com.albba.albbaDaeta.dto.DaetaAcceptDto;
import com.albba.albbaDaeta.repository.DaetaRepository;
import com.albba.albbaDaeta.repository.DaetaRequestRepository;
import com.albba.albbaDaeta.table.Daeta;
import com.albba.albbaDaeta.table.DaetaRequest;
import com.albba.albbaUser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DaetaService {
    private final UserRepository userRepository;
    private final DaetaRequestRepository daetaRequestRepository;
    private final DaetaRepository daetaRepository;

    public void requestSave(DaetaRequest request)
    {
        daetaRequestRepository.save(request);
    }

    public void accept( DaetaAcceptDto acceptDto)
    {
        //System.out.println(acceptDto.getNo());
        DaetaRequest request =daetaRequestRepository.findByNo(acceptDto.getNo());
        //System.out.println(request.getDate());
        String realname =userRepository.findByUserId(acceptDto.getAcceptId()).getRealname();
        //System.out.println(realname);
        daetaRepository.save(new Daeta(request, realname));
    }

    //이건 한달 근무표에서 쓸거
    public List<Daeta> daetaView(Long date,Long storeId)
    {
        return daetaRepository.findDaetasByDateAndStoreIdAndApproved(date,storeId,(long)1);
    }






}
