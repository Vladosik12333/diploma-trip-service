package by.vb.diplomatripservice.mapper;

public interface Mapper<S, T> {
	T mapTo(S source);
}
