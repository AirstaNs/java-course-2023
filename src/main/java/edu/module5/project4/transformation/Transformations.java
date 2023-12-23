package edu.module5.project4.transformation;

import edu.module5.project4.transformation.types.Transformation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class Transformations {
    private final List<Transformation> transformations;

    private Transformations(List<Transformation> transformations) {
        this.transformations = transformations;
    }

    public static Transformations all(Transformation popcorn, Transformation complex) {
        return new TransformationsBuilder().add(TransformationType.Heart.create())
                                           .add(TransformationType.Spherical.create())
                                           .add(TransformationType.Polar.create())
                                           .add(TransformationType.Spiral.create())
                                           .add(popcorn)
                                           .add(complex)
                                           .build();
    }

    public static class TransformationsBuilder {
        private final Set<Transformation> set;

        public TransformationsBuilder() {
            this.set = new HashSet<>();
        }

        public TransformationsBuilder add(Transformation transform) {
            set.add(transform);
            return this;
        }

        public TransformationsBuilder addAll(Set<Transformation> transforms) {
            set.addAll(transforms);
            return this;
        }

        public Transformations build() {
            var list = new ArrayList<>(set);
            return new Transformations(list);
        }
    }
}
