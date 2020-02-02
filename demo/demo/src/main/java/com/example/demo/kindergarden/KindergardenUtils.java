package com.example.demo.kindergarden;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.kindergarden.model.ChildrenModel;
import com.example.demo.kindergarden.model.KindergardenModel;

public class KindergardenUtils {
    public static Map<Integer, ChildrenModel> getPrioritizedListforKindergarden(KindergardenModel updatedKindergarden) {
        List<ChildrenModel> newChildrenQueue = updatedKindergarden.getChildrenQueue();
        Map<Integer, ChildrenModel> queueWithNumbers = new HashMap();
        for (int i = 0; i < newChildrenQueue.size(); i++) {
            queueWithNumbers.put(i, newChildrenQueue.get(i));
        }
        return queueWithNumbers;
    }

}