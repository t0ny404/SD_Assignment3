package sd.assignment.Controller;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.DTO.ResponseDTO;
import sd.assignment.Service.FoodService;
import sd.assignment.Service.Utils.Severity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path="/menu")
public class MenuController {

    @Autowired
    private FoodService foodService;

    @GetMapping("all")
    @ResponseBody
    public HashMap<String, List<String>> getFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("{rId}")
    @ResponseBody
    public List<FoodDTO> getFoodsByRId(@PathVariable Integer rId) {
        return foodService.getFoodsByRId(rId);
    }

    @GetMapping("pdf/{rId}")
    @ResponseBody
    public byte[] getMenuPDF(@PathVariable Integer rId) { return foodService.getMenuPDF(rId); }

    @PostMapping("add")
    public ResponseEntity addFood(@RequestBody FoodDTO foodDTO) {
        try {
            foodService.add(foodDTO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage(), Severity.FAILURE));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("Food added!", Severity.SUCCESS));
    }
}
