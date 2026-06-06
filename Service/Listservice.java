@Service
@RequiredArgsConstructor
public class MenuService {

    private final HotelDao hotelDao;

    private final FlightDao flightDao;

    public List<HotelEntity> getHotels() {

        return hotelDao.selectAll();
    }

    public List<FlightEntity> getFlights() {

        return flightDao.selectAll();
    }
}