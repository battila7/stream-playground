package playground;

import countries.Country;
import countries.CountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Application {
    public static void main(String[] args) {
        final var repository = new CountryRepository();
        final var countries = repository.getAll();

        // Feladat
        // Gyűjtsük össze azokat az országokat, melyeknek a neve hosszabb, mint 10 karakter.
        withoutStreams(countries);
        nestedStaticClass(countries);
        anonymousClass(countries);
        lambdaWithTypeAnnotationsAndBlockBody(countries);
        lambda(countries);
        methodReference(countries);
        mapAndMethodReference(countries);
    }

    private static void withoutStreams(final List<Country> countries) {
        final var result = new ArrayList<Country>();

        for (var country : countries) {
            if (country.name().length() > 10) {
                result.add(country);
            }
        }

        System.out.println(result.size());
    }

    private static class LongCountryNamePredicate implements Predicate<Country> {
        @Override
        public boolean test(Country country) {
            return country.name().length() > 10;
        }
    }

    private static void nestedStaticClass(final List<Country> countries) {
        final var result = countries.stream()
                .filter(new LongCountryNamePredicate())
                .toList();

        System.out.println(result.size());
    }

    private static void anonymousClass(final List<Country> countries) {
        final var result = countries.stream()
                .filter(new Predicate<Country>() {
                    @Override
                    public boolean test(Country country) {
                        return country.name().length() > 10;
                    }
                })
                .toList();

        System.out.println(result.size());
    }

    private static void lambdaWithTypeAnnotationsAndBlockBody(final List<Country> countries) {
        final var result = countries.stream()
                .filter((Country country) -> {
                    return country.name().length() > 10
                })
                .toList();

        System.out.println(result.size());
    }

    private static void lambda(final List<Country> countries) {
        final var result = countries.stream()
                .filter(country -> country.name().length() > 10)
                .toList();

        System.out.println(result.size());
    }

    private static boolean isLongCountryName(final Country country) {
        return country.name().length() > 10;
    }

    private static void methodReference(final List<Country> countries) {
        final var result = countries.stream()
                .filter(Application::isLongCountryName)
                .toList();

        System.out.println(result.size());
    }

    private static void mapAndMethodReference(final List<Country> countries) {
        final var result = countries.stream()
                .map(Country::name)
                .filter(name -> name.length() > 10)
                .toList();

        System.out.println(result.size());
    }
}
