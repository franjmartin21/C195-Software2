package lambda.dos_dos;

public class FindMatchingAnimals {
    private static void print(Animal animal, CheckTrait trait) {
        if(trait.test(animal))
            System.out.println(animal);
    }

    public static void main(String[] args) {
        print(new Animal("fish", false, true), (Animal a) -> {return a.canHop();});
        print(new Animal("kangaroo", true, false), a -> a.canHop());
    }
}
