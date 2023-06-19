package me.n1ar4.core;

import me.n1ar4.core.asm.MethodCallClassVisitor;
import me.n1ar4.db.entity.ClassFileEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MethodCall {
    private static final Logger logger = LogManager.getLogger(MethodCall.class);

    public static void start(Set<ClassFileEntity> classFileList, HashMap<MethodReference.Handle,
            HashSet<MethodReference.Handle>> methodCalls) {
        logger.info("start discovery information");
        for (ClassFileEntity file : classFileList) {
            try {
                MethodCallClassVisitor mcv =
                        new MethodCallClassVisitor(methodCalls);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(mcv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
