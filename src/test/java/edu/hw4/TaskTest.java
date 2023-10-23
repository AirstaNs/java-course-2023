package edu.hw4;

import edu.hw4.validator.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    private static final Map<Animal.Type, Animal> animalData;

    static {
        animalData = Map.of(
            Animal.Type.DOG,
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 50, 20, false),

            Animal.Type.CAT,
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 30, 10, false),

            Animal.Type.FISH,
            new Animal("FishyFish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 5, false),

            Animal.Type.BIRD,
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 2, 20, 8, false)
        );
    }

    @Test
    @DisplayName("Животные должны быть отсортированы по росту от самого маленького к самому большому.")
    public void testTask1() {
        // Given
        List<Animal> expected = List.of(
            animalData.get(Animal.Type.FISH),
            animalData.get(Animal.Type.BIRD),
            animalData.get(Animal.Type.CAT),
            animalData.get(Animal.Type.DOG)
        );
        ArrayList<Animal> animals = new ArrayList<>(expected);
        Collections.shuffle(animals);
        // When
        List<Animal> result = Tasks.task1(animals);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Животные должны быть отсортированы по весу от самого тяжелого к самому легкому, и выбраны первые k.")
    public void testTask2() {
        // Given
        int count = 2;
        List<Animal> expected = List.of(
            animalData.get(Animal.Type.DOG),
            animalData.get(Animal.Type.CAT),
            animalData.get(Animal.Type.BIRD)
        );
        ArrayList<Animal> animals = new ArrayList<>(expected);
        Collections.shuffle(animals);

        // When
        List<Animal> result = Tasks.task2(animals, count);

        // Then
        assertEquals(expected.subList(0, count), result);
    }


    @Test
    @DisplayName("Должно возвращать корректное количество каждого типа животного.")
    public void testTask3() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(animalData.get(Animal.Type.DOG));

        // When
        Map<Animal.Type, Integer> result = Tasks.task3(animals);

        // Then
        assertEquals(2, result.get(Animal.Type.DOG));
        assertEquals(1, result.get(Animal.Type.CAT));
        assertEquals(1, result.get(Animal.Type.FISH));
        assertEquals(1, result.get(Animal.Type.BIRD));
    }

    @Test
    @DisplayName("Должно возвращать животное с самым длинным именем.")
    public void testTask4() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        String expected = animalData.get(Animal.Type.FISH).name();

        // When
        Animal result = Tasks.task4(animals);

        // Then
        assertEquals(expected, result.name());
    }

    @Test
    @DisplayName("Должно корректно определять, каких животных больше: самцов или самок.")
    public void testTask5() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());

        // When
        Animal.Sex result = Tasks.task5(animals);

        // Then
        assertEquals(Animal.Sex.M, result);
    }

    @Test
    @DisplayName("Должно возвращать самое тяжелое животное каждого вида.")
    public void testTask6() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 6, 55, 25, false));
        animals.add(new Animal("Fish2", Animal.Type.FISH, Animal.Sex.F, 1, 12, 6, false));

        // When
        Map<Animal.Type, Animal> result = Tasks.task6(animals);

        // Then
        assertEquals("Dog2", result.get(Animal.Type.DOG).name());
        assertEquals("Fish2", result.get(Animal.Type.FISH).name());
        assertEquals("Cat", result.get(Animal.Type.CAT).name());
        assertEquals("Bird", result.get(Animal.Type.BIRD).name());
    }

    @Test
    @DisplayName("Должно возвращать k-е самое старое животное.")
    public void testTask7() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 7, 55, 25, false));  // Добавим еще одну старую собаку

        // When
        Animal result1 = Tasks.task7(animals, 1);
        Animal result2 = Tasks.task7(animals, 3);

        // Then
        assertEquals("Dog2", result1.name());
        assertEquals("Cat", result2.name());
    }

    @Test
    @DisplayName("Должно возвращать самое тяжелое животное среди животных ниже k см.")
    public void testTask8() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        String longestName = "Fish2";
        animals.add(new Animal(longestName, Animal.Type.FISH, Animal.Sex.F, 1, 5, 7, false));  // Этот рыба тяжелее и ниже

        // When
        Optional<Animal> result = Tasks.task8(animals, 10);

        // Then
        assertTrue(result.isPresent());
        assertEquals(longestName, result.get().name());
    }

    @Test
    @DisplayName("Должно возвращать общее количество лап у всех животных в списке.")
    public void testTask9() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 8, false));  // Паук с 8 лапами
        int expected = 18;

        // When
        int result = Tasks.task9(animals);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Должно возвращать список животных, у которых возраст не равен количеству лап.")
    public void testTask10() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.remove(animalData.get(Animal.Type.BIRD));

        // When
        List<Animal> result = Tasks.task10(animals);

        // Then
        assertEquals(animals, result);
    }

    @Test
    @DisplayName("Должно возвращать список животных, которые могут укусить и рост которых превышает 100 см.")
    public void testTask11() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        Animal bigDog = new Animal("BigDog", Animal.Type.DOG, Animal.Sex.M, 3, 110, 4, true);
        animals.add(bigDog);  // Большая собака, которая может укусить
        List<Animal> expected = List.of(bigDog);

        // When
        List<Animal> result = Tasks.task11(animals);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Должно возвращать количество животных, у которых вес превышает рост.")
    public void testTask12() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(new Animal("BigBird", Animal.Type.BIRD, Animal.Sex.M, 2, 25, 111, false));  // Птица, вес которой превышает рост
        int expected = 1; // BigBird

        // When
        int result = Tasks.task12(animals);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Должно возвращать список животных, имена которых состоят из более чем двух слов.")
    public void testTask13() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(new Animal("Pretty Little Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 15, 2, false));
        List<Animal> expected = List.of(new Animal("Pretty Little Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 15, 2, false));

        // When
        List<Animal> result = Tasks.task13(animals);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Должно возвращать true, если есть собака ростом более k см в списке.")
    public void testTask14() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(new Animal("BigDog", Animal.Type.DOG, Animal.Sex.M, 3, 110, 4, true));  // Большая собака с ростом 110 см

        // When
        boolean result = Tasks.task14(animals, 100);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Должно возвращать false, если нет собаки ростом более k см в списке.")
    public void testTask14Negative() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());

        // When
        boolean result = Tasks.task14(animals, 100);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Должно возвращать суммарный вес животных каждого вида, которым от k до l лет.")
    public void testTask15() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(new Animal("ElderDog", Animal.Type.DOG, Animal.Sex.M, 7, 70, 4, true));
        animals.add(new Animal("ElderCat", Animal.Type.CAT, Animal.Sex.F, 6, 35, 4, false));
        animals.add(new Animal("YoungDog", Animal.Type.DOG, Animal.Sex.F, 1, 15, 4, true));
        var expected = Map.of(Animal.Type.CAT,4);

        // When
        var result = Tasks.task15(animals, 5, 7);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Должно возвращать список животных, отсортированный по виду, затем по полу, затем по имени.")
    public void testTask16() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        List<Animal> expected = List.of(
            animalData.get(Animal.Type.CAT),
            animalData.get(Animal.Type.DOG),
            animalData.get(Animal.Type.BIRD),
            animalData.get(Animal.Type.FISH)
        );

        // When
        List<Animal> result = Tasks.task16(animals);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Должно проверить, правда ли, что пауки кусаются чаще, чем собаки.")
    public void testTask17() {
        // Given
        List<Animal> animals = new ArrayList<>(animalData.values());
        animals.add(new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 8, true));
        animals.add(new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 2, 2, 8, true));

        // When
        Boolean result = Tasks.task17(animals);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Должно найти самую тяжелую рыбку в 2-х или более списках.")
    public void testTask18() {
        // Given
        Animal bigFish =  new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 2, 10, 0, false);
        List<Animal> list1 = new ArrayList<>();
        list1.add(new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 2, 10, 0, false));
        list1.add(new Animal("Fish2", Animal.Type.FISH, Animal.Sex.F, 3, 5, 0, false));

        List<Animal> list2 = new ArrayList<>();
        list2.add(new Animal("Fish3", Animal.Type.FISH, Animal.Sex.M, 1, 12, 0, false));
        list2.add(new Animal("Dog4", Animal.Type.DOG, Animal.Sex.F, 4, 50, 4, true));

        List<List<Animal>> listAnimals = List.of(list1, list2);

        // When
        Animal result = Tasks.task18(listAnimals);

        // Then
        assertEquals(bigFish, result);
    }

    @Test
    @DisplayName("Должно идентифицировать животных с ошибками.")
    public void testTask19() {
        // Given
        List<Animal> animals = List.of(
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, -1, 50, 25, true),  // Ошибка в возрасте
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 5, -10, 4, false),  // Ошибка в росте
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Bird1", Animal.Type.BIRD, Animal.Sex.F, 3, 15, -1, true)   // Ошибка в весе
        );

        // When
        Map<String, Set<ValidationError>> errors = Tasks.task19(animals);

        // Then
        assertEquals(4, errors.size());

        assertTrue(errors.containsKey("Dog1"));
        assertTrue(errors.containsKey("Cat1"));
        assertTrue(errors.containsKey("Bird1"));
        assertTrue(errors.containsKey("Fish1"));
    }

    @Test
    @DisplayName("Должно форматировать ошибки для более простого чтения.")
    public void testTask20() {
        // Given
        List<Animal> animals = List.of(
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, -1, 50, 25, true),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 5, -10, 4, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Bird1", Animal.Type.BIRD, Animal.Sex.F, 3, 15, -1, true)
        );

        // When
        Map<String, String> formattedErrors = Tasks.task20(animals);

        // Then
        assertTrue(formattedErrors.containsKey("Dog1"));
        assertTrue(formattedErrors.containsKey("Cat1"));
        assertTrue(formattedErrors.containsKey("Bird1"));

        assertEquals("age", formattedErrors.get("Dog1"));
        assertEquals("height", formattedErrors.get("Cat1"));
        assertEquals("weight", formattedErrors.get("Fish1"));
        assertEquals("weight", formattedErrors.get("Bird1"));
    }
}
