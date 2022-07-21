package hr.tvz.zendeli.vaxapp;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class SampleJob extends QuartzJobBean {
    @Autowired
    private VaccineService vaccineService;



    private String name;
    private static List<VaccineDTO> vaccineDTOList;
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.print("Ovo su trenutno dostupna cjepiva");
         vaccineDTOList= vaccineService.findByStockOfDosesGreaterThen(0);


        for(int i=0; i< vaccineDTOList.size(); i++){

            System.out.print(vaccineDTOList.get(i).getResearchName()+"-----"+vaccineDTOList.get(i).getAvailableDoses()+"\n");

        }


    }
}
