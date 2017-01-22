package com.viacheslav.khpi;

import com.viacheslav.khpi.decoder.entity.Sequence;
import com.viacheslav.khpi.decoder.entity.SequenceKind;
import com.viacheslav.khpi.decoder.logic.SequenceContainer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TestSequenceContainer {
    String inputSequence;
    SequenceKind inputSequenceKind;
    List<Sequence> expectedSequence;

    public TestSequenceContainer(String inputSequence, SequenceKind inputSequenceKind, List<Sequence> expectedSequence){
        this.inputSequence = inputSequence;
        this.inputSequenceKind = inputSequenceKind;
        this.expectedSequence = expectedSequence;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Sequence> param1 = new ArrayList<>();
        param1.add(new com.viacheslav.khpi.decoder.entity.Sequence("abc", 1));
        param1.add(new com.viacheslav.khpi.decoder.entity.Sequence("def", 1));
        List<Sequence> param2 = new ArrayList<>();
        param2.add(new com.viacheslav.khpi.decoder.entity.Sequence("x", 3));
        param2.add(new com.viacheslav.khpi.decoder.entity.Sequence("y", 2));
        param2.add(new com.viacheslav.khpi.decoder.entity.Sequence("z", 1));
        Object[][] data = {
                {"abc def",SequenceKind.WORD,param1},
                {"x y z x x y",SequenceKind.BIGRAMM,param2}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testAddSequencesToContainerPositive(){
        SequenceContainer sequenceContainer = new SequenceContainer();
        sequenceContainer.addSequencesToContainer(inputSequence, inputSequenceKind);
        List<Sequence> sequences = sequenceContainer.sequences;
        for(int i = 0; i < sequences.size(); i++){
            Assert.assertTrue(expectedSequence.get(i).equals(sequences.get(i)));
        }
    }
}
