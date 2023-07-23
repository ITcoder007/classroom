package demo.function.stream;

@FunctionalInterface
public interface ToyFunction<R,T,S> {
    R run(T t ,S s);
}
