package edu.module3.hw4;

import edu.module3.hw4.validator.AgeValidator;
import edu.module3.hw4.validator.CompositeAnimalValidator;
import edu.module3.hw4.validator.SizeValidator;
import edu.module3.hw4.validator.ValidationError;
import edu.module3.hw4.validator.Validator;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Tasks {

    private Tasks() {
    }

    // 1. Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public static List<Animal> task1(List<Animal> animals) {
        Comparator<Animal> comparator = Comparator.comparingInt(Animal::height);
        return animals.stream()
            .sorted(comparator)
            .toList();
    }

    // 2. Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public static List<Animal> task2(List<Animal> animals, int count) {
        Comparator<Animal> reversed = Comparator.comparingInt(Animal::weight).reversed();
        return animals.stream()
            .sorted(reversed)
            .limit(count)
            .toList();
    }

    // 3. Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public static Map<Animal.Type, Integer> task3(List<Animal> animals) {
        return animals.stream()
            .map(Animal::type)
            .collect(
                Collectors.groupingBy(
                    Function.identity(),
                    Collectors.collectingAndThen(
                        Collectors.counting(),
                        Long::intValue
                    )
                )
            );
    }

    // 4. У какого животного самое длинное имя -> Animal
    public static Animal task4(List<Animal> animals) {
        Comparator<Animal> comparator = Comparator.comparingInt(animal -> animal.name().length());
        return animals
            .stream()
            .max(comparator)
            .orElseThrow(RuntimeException::new);
    }

    // 5. Каких животных больше: самцов или самок -> Sex
    public static Animal.Sex task5(List<Animal> animals) {
        Predicate<Animal> isMale = (animal -> animal.sex() == Animal.Sex.M);
        Predicate<Animal> isNotNull = (animal -> animal.sex() != null);

        var collector = Collectors.partitioningBy(isNotNull.and(isMale));
        var partitioned = animals.stream().collect(collector);
        int sizeMale = partitioned.get(Boolean.TRUE).size();
        int sizeFemale = partitioned.get(Boolean.FALSE).size();
        return sizeMale > sizeFemale ? Animal.Sex.M : Animal.Sex.F;
    }

    // 6. Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        Comparator<Animal> comparator = Comparator.comparingInt(Animal::weight);
        var groupCollector = Collectors.toMap(
            Animal::type,
            Function.identity(),
            BinaryOperator.maxBy(comparator)
        );
        return animals.stream().collect(groupCollector);
    }

    // 7. K-е самое старое животное -> Animal
    public static Animal task7(List<Animal> animals, int count) {
        Comparator<Animal> comparator = Comparator.comparingInt(Animal::age).reversed();
        return animals.stream()
            .sorted(comparator)
            .skip(count - 1)
            .findFirst()
            .orElse(null);

    }

    // 8. Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public static Optional<Animal> task8(List<Animal> animals, int maxHeight) {
        Comparator<Animal> reversedWeight = Comparator.comparingInt(Animal::weight).reversed();
        Predicate<Animal> boundHeight = animal -> animal.height() < maxHeight;
        return animals.stream()
            .filter(boundHeight)
            .max(reversedWeight);
    }

    // 9. Сколько в сумме лап у животных в списке -> Integer
    public static Integer task9(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    // 10. Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public static List<Animal> task10(List<Animal> animals) {
        Predicate<Animal> ageNotEqualPaws = animal -> animal.age() != animal.paws();
        return animals.stream()
            .filter(ageNotEqualPaws)
            .toList();
    }

    // 11. Список животных, которые могут укусить (bites == true)
    // и рост которых превышает 100 см -> List<Animal>
    public static List<Animal> task11(List<Animal> animals) {
        final int boundHeight = 100;
        Predicate<Animal> predicateBoundHeight =  animal -> animal.height() > boundHeight;
        return animals.stream()
            .filter(Animal::bites)
            .filter(predicateBoundHeight)
            .toList();
    }

    // 12. Сколько в списке животных, вес которых превышает рост -> Integer
    public static Integer task12(List<Animal> animals) {
        Predicate<Animal> weightMoreHeight = animal -> animal.weight() > animal.height();
        return (int) animals.stream()
                            .filter(weightMoreHeight)
                            .count();
    }

    // 13. Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public static List<Animal> task13(List<Animal> animals) {
        Predicate<Animal> nameConsistMoreTwoWords = animal -> animal.name().split("\\s+").length > 2;
        return animals.stream()
            .filter(nameConsistMoreTwoWords)
            .collect(Collectors.toList());
    }

    // 14. Есть ли в списке собака ростом более k см -> Boolean
    public static Boolean task14(List<Animal> animals, int minHeight) {
        Predicate<Animal> isTypeDog = animal -> animal.type() == Animal.Type.DOG;
        Predicate<Animal> boundHeight = animal -> animal.height() > minHeight;
        return animals.stream()
            .filter(isTypeDog)
            .anyMatch(boundHeight);
    }

    // 15. Найти суммарный вес животных каждого вида, которым от k до l лет -> Integer
    public static Map<Animal.Type, Integer> task15(List<Animal> animals, int minAge, int maxAge) {
        Predicate<Animal> ageBetween = animal -> animal.age() > minAge && animal.age() < maxAge;

        var groupCollector = Collectors.groupingBy(
            Animal::type,
            Collectors.summingInt(Animal::weight)
        );
        return animals.stream()
            .filter(ageBetween)
            .collect(groupCollector);
    }

    // 16. Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public static List<Animal> task16(List<Animal> animals) {
        return animals.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                          .thenComparing(Animal::sex)
                          .thenComparing(Animal::name)
            ).toList();
    }

    // 17. Правда ли, что пауки кусаются чаще, чем собаки -> Boolean
    // (если данных для ответа недостаточно, вернуть false)
    public static Boolean task17(List<Animal> animals) {
        Predicate<Animal> isDog = animal -> animal.type() == Animal.Type.DOG;
        Predicate<Animal> isSpider = animal -> animal.type() == Animal.Type.SPIDER;
        var groupCollector = Collectors.groupingBy(
            Animal::type,
            Collectors.counting()
        );
        var groups = animals.stream()
            .filter(isDog.or(isSpider))
            .collect(groupCollector);

        Long dogs = groups.get(Animal.Type.DOG);
        Long spiders = groups.get(Animal.Type.SPIDER);
        if ((dogs & spiders) < 0) {
            return false;
        }
        return spiders > dogs;
    }

    // 18. Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    public static Animal task18(List<List<Animal>> listAnimals) {
        Predicate<Animal> isFish = animal -> animal.type() == Animal.Type.FISH;
        return listAnimals.stream()
            .flatMap(Collection::stream)
            .filter(isFish)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    // 19. Животные, в записях о которых есть ошибки:
    //     вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.
    //      Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно.
    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        List<Validator> validators = List.of(new AgeValidator(), new SizeValidator());
        CompositeAnimalValidator validator = new CompositeAnimalValidator(validators);
        return animals.stream()
            .map(animal -> new AbstractMap.SimpleEntry<>(animal.name(), validator.validate(animal)))
            .filter(entry -> !entry.getValue().isEmpty()) // фильтрация записей без ошибок
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    // 20. Сделать результат предыдущего задания более читабельным:
    //     вернуть имя и названия полей с ошибками,
    //     объединенные в строку -> Map<String, String>
    public static Map<String, String> task20(List<Animal> animals) {
        Map<String, Set<ValidationError>> animalErrors = task19(animals);
        return animalErrors.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(ValidationError::getFieldName)
                    .collect(Collectors.joining(", "))
            ));
    }
}

