package bg.softuni.healthheaven.config;

import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.ArticleRepository;
import bg.softuni.healthheaven.repositories.ItemRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public User dataSourceInitializer(UserRepository userRepository,
                                      PasswordEncoder passwordEncoder,
                                      ItemRepository itemRepository,
                                      ArticleRepository articleRepository) {

        if (itemRepository.count() == 0) {
            Item item0 = new Item();
            item0.setName("Vitamin C Supplement");
            item0.setPrice(20.00);
            item0.setDescription("High-quality vitamin C supplement to boost immunity. This supplement provides the necessary daily dose of vitamin C, which is essential for the growth, development, and repair of all body tissues. It is involved in many body functions, including the formation of collagen, absorption of iron, proper functioning of the immune system, wound healing, and the maintenance of cartilage, bones, and teeth. Regular intake can help protect against immune system deficiencies.");
            item0.setImageURL("https://www.health.com/thmb/X7x_mOg1fiZAR24xx5QfYP4fce0=/fit-in/1500x1000/filters:no_upscale():max_bytes(150000):strip_icc()/nature-made-vitamin-c-500-mg-d16021baf05b47dc94433c65ab590da7.jpg");

            Item item1 = new Item();
            item1.setName("Multivitamin Tablets");
            item1.setPrice(30.00);
            item1.setDescription("Comprehensive multivitamin for daily nutritional needs. These tablets are designed to fill nutritional gaps and ensure that you get your daily recommended intake of essential vitamins and minerals. They support overall health, boost energy levels, enhance immune function, and promote healthy skin, hair, and nails. With a balanced formula, these multivitamins help you maintain a healthy lifestyle, making sure your body gets the nutrients it needs to function optimally every day.");
            item1.setImageURL("https://m.media-amazon.com/images/I/51oQrGnRdTL.jpg");

            Item item2 = new Item();
            item2.setName("Pain Relief Tablets");
            item2.setPrice(15.00);
            item2.setDescription("Effective pain relief tablets for headaches and muscle pain. These tablets provide fast and effective relief from various types of pain, including headaches, muscle aches, back pain, and arthritis. Formulated with powerful painkillers, they work quickly to alleviate discomfort and help you get back to your daily activities. Whether you're dealing with chronic pain or occasional aches, these tablets are a reliable solution to help manage your symptoms and improve your quality of life.");
            item2.setImageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1DzmeuHx8ii4naUzhxpEBIGsN5-AM4fj44w&s");

            Item item4 = new Item();
            item4.setName("Cold and Flu Tablets");
            item4.setPrice(18.00);
            item4.setDescription("Tablets to alleviate cold and flu symptoms. These tablets are specially formulated to provide relief from the common symptoms of cold and flu, including fever, chills, body aches, sore throat, and congestion. They contain a blend of ingredients that work together to reduce fever, ease pain, and clear nasal congestion, helping you recover faster and feel better sooner. Ideal for keeping on hand during the cold and flu season to manage symptoms effectively.");
            item4.setImageURL("https://phelans.ie/cdn/shop/products/AdvilCold_FluTablets20Pack_800x.jpg?v=1604490858");

            Item item5 = new Item();
            item5.setName("Antacid Tablets");
            item5.setPrice(12.00);
            item5.setDescription("Quick relief from heartburn and indigestion. These antacid tablets provide fast-acting relief from the discomfort of heartburn, acid indigestion, and upset stomach. Formulated to neutralize stomach acid on contact, they help to quickly alleviate the burning sensation associated with acid reflux. Whether you've overindulged or are dealing with chronic digestive issues, these tablets can help restore comfort and allow you to enjoy your meals without worry.");
            item5.setImageURL("https://i5.walmartimages.com/asr/455a601c-d69f-4046-9e6b-55d29fcefc43_1.8353eff8576b9a758e5af63f6c2696f9.jpeg");

            Item item6 = new Item();
            item6.setName("Fish Oil Supplements");
            item6.setPrice(22.00);
            item6.setDescription("Omega-3 rich fish oil supplements for heart health. These supplements are packed with omega-3 fatty acids, which are essential for maintaining cardiovascular health. Regular intake can help reduce inflammation, lower blood pressure, decrease triglycerides, and reduce the risk of heart disease. Additionally, omega-3s support brain health, joint function, and overall well-being. These fish oil supplements are a convenient way to ensure you get enough of these crucial nutrients.");
            item6.setImageURL("https://m.media-amazon.com/images/I/71m62G2Sr3L.jpg");

            Item item7 = new Item();
            item7.setName("Probiotics");
            item7.setPrice(28.00);
            item7.setDescription("Probiotic capsules for digestive health. These probiotics contain live beneficial bacteria that support a healthy gut microbiome. Regular use can help improve digestion, enhance nutrient absorption, boost immune function, and maintain overall gastrointestinal health. Ideal for individuals dealing with digestive issues such as bloating, gas, and irregularity, these probiotic capsules are an excellent addition to your daily health regimen.");
            item7.setImageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDfsgem4E4K7WGIOW_PA9_sspBfVEDWcXpHg&s");

            Item item8 = new Item();
            item8.setName("Cough Syrup");
            item8.setPrice(14.00);
            item8.setDescription("Soothing syrup for cough relief. This cough syrup is formulated to provide effective relief from persistent coughs. It contains ingredients that help to soothe the throat, reduce irritation, and suppress the cough reflex. Whether you're dealing with a dry, tickly cough or a chesty, productive cough, this syrup can help ease your symptoms and provide much-needed comfort. Keep it on hand during cold and flu season for fast relief.");
            item8.setImageURL("https://5.imimg.com/data5/SELLER/Default/2023/8/338004786/LD/SN/AI/125278182/dry-cough-syrup-newcuf-dx-cough-syrup-500x500.jpeg");

            Item item9 = new Item();
            item9.setName("Sleep Aids");
            item9.setPrice(20.00);
            item9.setDescription("Tablets to help improve sleep quality. These sleep aids are designed to help you fall asleep faster and stay asleep longer. They contain natural and effective ingredients that promote relaxation and support a healthy sleep cycle. Whether you're dealing with occasional sleeplessness or chronic insomnia, these tablets can help you achieve restful, rejuvenating sleep, so you wake up feeling refreshed and ready to take on the day.");
            item9.setImageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY3MDlGCLuF1j-lyQG0FmdXbxn_NUIkVu5FA&s");


            itemRepository.saveAll(List.of(item0, item2, item1, item4, item5, item6, item7, item8, item9));
        }

        if (articleRepository.count() == 0) {



        }
        if (userRepository.count() == 0) {
            User user = new User();
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setEmail("admin@gmail.com");
            user.setRole(RoleEnum.ADMIN);
            user.setPassword(passwordEncoder.encode("admin"));
            return userRepository.save(user);
        }

        return null;
    }
}
