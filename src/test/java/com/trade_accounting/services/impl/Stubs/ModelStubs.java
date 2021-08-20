package com.trade_accounting.services.impl.Stubs;

import com.trade_accounting.models.AccessParameters;
import com.trade_accounting.models.Address;
import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.AttributeOfCalculationObject;
import com.trade_accounting.models.BalanceAdjustment;
import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contact;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.ContractorGroup;
import com.trade_accounting.models.ContractorStatus;
import com.trade_accounting.models.Correction;
import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Currency;
import com.trade_accounting.models.Department;
import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Inventarization;
import com.trade_accounting.models.InventarizationProduct;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.Payment;
import com.trade_accounting.models.PaymentMethods;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.Production;
import com.trade_accounting.models.Project;
import com.trade_accounting.models.RequestsProductions;
import com.trade_accounting.models.ReturnToSupplier;
import com.trade_accounting.models.Role;
import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.TaxSystem;
import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.TechnicalCardGroup;
import com.trade_accounting.models.TechnicalCardProduction;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.TypeOfPayment;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.fias.City;
import com.trade_accounting.models.fias.District;
import com.trade_accounting.models.fias.FiasAddressModel;
import com.trade_accounting.models.fias.Region;
import com.trade_accounting.models.fias.Street;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.trade_accounting.services.impl.Stubs.model.InvoiceModelStubs.getInvoice;

public class ModelStubs {
    //TODO Вынести заглушки моделей из классов сервисов сюда

    public static AccessParameters getAccessParameters(Long id) {
        return new AccessParameters(id, false, getEmployee(id), getDepartment(id));
    }

    public static AttributeOfCalculationObject getAttributeOfCalculationObject(Long id) {
        return new AttributeOfCalculationObject(id, "name", "00001", true);
    }

    public static Payment getPayment(Long id) {
        return Payment.builder()
                .id(id)
                .company(getCompany(1L))
                .contract(getContract(1L))
                .contractor(getContractor(1L))
                .paymentMethods(PaymentMethods.BANK)
                .number("100")
                .sum(BigDecimal.valueOf(100L))
                .time(LocalDateTime.now())
                .typeOfPayment(TypeOfPayment.INCOMING)
                .project(Project.builder()
                        .id(1L)
                        .name("name")
                        .code("code")
                        .description("description")
                        .build())
                .build();
    }

    public static Company getCompany(Long id) {
        return new Company(
                id, "name",
                "inn", "00001",
                "89040408488", "3420943",
                "email", true, getAddress(1L),
                "commentToAddress", "leader",
                "leaderManagerPos", "signatureOfLider",
                "cheidAcc", "aaaaa", "stamp",
                getLegalDetail(id),
                Stream.of(
                        getBankAccount(1L),
                        getBankAccount(2L),
                        getBankAccount(3L)
                ).collect(Collectors.toList())
        );
    }

    public static Contractor getContractor(Long id) {
        return new Contractor(
                id, "name",
                "sortNumber",
                "12345678901", "324234234",
                "email", getAddress(1L),
                "commentToAddress", "comment",
                "1234",
                Stream.of(
                        getContact(1L),
                        getContact(2L),
                        getContact(3L)
                ).collect(Collectors.toList()),
                getContractorGroup(id), // getTypeOfContractor(id),
                getTypeOfPrice(id),
                Stream.of(
                        getBankAccount(1L),
                        getBankAccount(2L),
                        getBankAccount(3L)
                ).collect(Collectors.toList()),
                getLegalDetail(id),
                getStatus(id),
                getAccessParameters(id)
        );
    }

    public static Contact getContact(Long id) {
        return Contact.builder().id(id).build();
    }

    public static Contract getContract(Long id) {
            return Contract.builder()
                    .id(id)
                    .number("00000" + id)
                    .contractDate(LocalDate.now())
                    .company(getCompany(1L))
                    .bankAccount(getBankAccount(3L))
                    .contractor(getContractor(1L))
                    .amount(BigDecimal.ONE)
                    .archive(false)
                    .comment("Comment " + id)
                    .legalDetail(getLegalDetail(1L))
                    .build();
    }

    public static Project getProject(Long id) {
        return new Project(
                id, "name",
                "00001", "description"
        );
    }

    public static BankAccount getBankAccount(Long id) {
        return new BankAccount(
                id, "rbic", "bank",
                "address", "correspon",
                "account", true, "00001"
        );
    }

    public static ContractorGroup getContractorGroup(Long id) {
        return new ContractorGroup(id, "name", "00001");
    }

    public static Address getAddress(Long id) {
        return Address.builder()
                .id(id)
                .index("123456")
                .country("Россия")
                .region("region")
                .city("city")
                .house("1")
                .apartment("1")
                .another("")
                .build();
    }

    public static Department getDepartment(Long id) {
        return new Department(id, "name", "00001");
    }

    public static TaxSystem getTaxSystem(Long id) {
        return new TaxSystem(id, "name", "00001");
    }

    public static Position getPosition(Long id) {
        return new Position(id, "name", "00001");
    }



    public static Employee getEmployee(Long id) {
        return new Employee(
                id, "lastName", "firstName",
                "middleName", "00001", "89030450020",
                "123456789012", "descript",
                "email.email@email.com", "pass",
                getDepartment(id), getPosition(id),
                Stream.of(
                        getRole(id),
                        getRole(id + 1),
                        getRole(id + 2)
                ).collect(Collectors.toSet()),
                getImage(id)
        );
    }

    public static Image getImage(Long id) {
        return Image.builder()
                .id(id)
                .imageUrl("url")
                .sortNumber("000" + id)
                .build();
    }

    public static ImageDto getImageDto(Long id) {
        return ImageDto.builder()
                .id(id)
                .content("content".getBytes())
                .fileExtension(".png")
                .sortNumber("000" + id)
                .build();
    }

    public static TypeOfContractor getTypeOfContractor(Long id) {
        return new TypeOfContractor(id, "name", "00001");
    }

    public static TypeOfPrice getTypeOfPrice(Long id) {
        return new TypeOfPrice(id, "name", "00001");
    }

    public static LegalDetail getLegalDetail(Long id) {
        return new LegalDetail(
                id, "lastName",
                "firstNAme", "middleName",
                getAddress(1L), "commentToAddress",
                "32432423", "kpp", "okpo", "ogrn",
                "numberOfCertifacate", LocalDate.now(), getTypeOfContractor(id)
        );
    }

    public static Role getRole(Long id) {
        return new Role(id, "name", "00001");
    }

//    public static Invoice getInvoice(Long id) {
//        return new Invoice(
//                id,
//                LocalDateTime.now(),
//                TypeOfInvoice.RECEIPT,
//                getCompany(id),
//                getContractor(id),
//                getWarehouse(id),
//                Boolean.TRUE,
//                "Комментарий"
//        );
//    }

    public static Currency getCurrency(Long id) {
        return new Currency(id, "rubles", "Russian Rubles", "25", "rub", "1");
    }

    public static Product getProduct(Long id) {
        return Product.builder()
                .id(id)
                .name("Яблоко")
                .weight(BigDecimal.TEN)
                .volume(BigDecimal.TEN)
                .purchasePrice(BigDecimal.ONE)
                .description("Description")
                .archive(false)
                .build();
    }

    public static ProductDto getProductDto(Long id) {
        return ProductDto.builder()
                .id(id)
                .name("Яблоко")
                .weight(BigDecimal.TEN)
                .volume(BigDecimal.TEN)
                .purchasePrice(BigDecimal.ONE)
                .description("Description")
                .archive(false)
                .build();
    }

    public static City getCity(Long id) {
        return City.builder()
                .id(id)
                .name("Petrpopavlovsk")
                .district(getDistrict(id))
                .streets(new ArrayList<>())
                .build();
    }

    public static District getDistrict(Long id) {
        return District.builder()
                .id(id)
                .name("Vasileostrivky")
                .region(getRegion(id))
                .cities(new ArrayList<>())
                .build();
    }

    public static Region getRegion(Long id) {
        return Region.builder()
                .id(id)
                .name("SKO")
                .districts(new ArrayList<>())
                .build();
    }

    public static FiasAddressModel getFiasAddressModel(Long id) {
        return FiasAddressModel.builder()
                .id(id)
                .aoguid("example")
                .aolevel("1")
                .formalname("formalName")
                .parentguid("parentguid")
                .shortname("shortname")
                .build();
    }

    public static ContractorStatus getStatus(Long id) {
        return ContractorStatus.builder()
                .id(id)
                .name("Новый")
                .build();
    }

    public static Street getStreet(Long id) {
        return Street.builder()
                .id(id)
                .name("Volodarskogo")
                .city(getCity(id))
                .build();
    }

    public static Production getProduction(Long id) {
        return Production.builder()
                .id(id)
                .technicalCard(getTechnicalCard(id))
                .requestsProductions(getRequestsProductions(id))
                .build();
    }

    public static TechnicalCard getTechnicalCard(Long id) {
        return TechnicalCard.builder()
                .id(id)
                .name("name")
                .comment("comment")
                .productionCost("productionCost")
                .technicalCardGroup(getTechnicalCardGroup(id))
                .finalProduction(Stream.of(
                        getTechnicalCardProduction(id),
                        getTechnicalCardProduction(id + 1),
                        getTechnicalCardProduction(id + 2)
                ).collect(Collectors.toList()))
                .materials(Stream.of(
                        getTechnicalCardProduction(id + 3),
                        getTechnicalCardProduction(id + 4),
                        getTechnicalCardProduction(id + 5)
                ).collect(Collectors.toList()))
                .build();
    }

    public static TechnicalCardGroup getTechnicalCardGroup(Long id) {
        return TechnicalCardGroup.builder()
                .id(id)
                .name("name")
                .comment("comment")
                .sortNumber("sortNumber")
                .build();
    }

    public static TechnicalCardProduction getTechnicalCardProduction(Long id) {
        return TechnicalCardProduction.builder()
                .id(id)
                .amount(1L)
                .product(getProduct(id))
                .build();
    }

    public static RequestsProductions getRequestsProductions(Long id) {
        return RequestsProductions.builder()
                .id(id)
                .numberOfTheCertificate("123")
                .dateOfTheCertificate(LocalDate.ofEpochDay(2021 - 06 - 01))
                .technicalCard(getTechnicalCard(id))
                .volume(2)
                .warehouse(getWarehouse(id))
                .build();
    }

    public static Warehouse getWarehouse(Long id) {
        return Warehouse.builder()
                .id(id)
                .name("name")
                .sortNumber("sortNamber")
                .address("address")
                .commentToAddress("commentToAddress")
                .comment("comment")
                .build();
    }

    public static Warehouse getWarehouse() {
        return new Warehouse(
                1L, "Склад 1", "1", "Володарского", "Комментарий 1",
                "Комментарий 2"
        );
    }

    public static ReturnToSupplier getReturnToSupplier(Long id) {
        return ReturnToSupplier.builder()
                .id(id)
                .comment("Комментарий 1")
                .company(getCompany(1L))
                .contract(getContract(1L))
                .contractor(getContractor(1L))
                .warehouse(getWarehouse(1L))
                .date(LocalDateTime.now().toString())
                .isPrint(false)
                .isSend(false)
                .build();
    }

    public static CorrectionProduct getCorrectionProduct(Long id) {
        return new CorrectionProduct(
                id,
                getProduct(id),
                BigDecimal.ONE,
                BigDecimal.ONE
        );
    }

    public static Correction getCorrection(Long id) {
        return new Correction(
                id, LocalDateTime.now(), getWarehouse(), getCompany(id),
                false, false, false,
                "Комментарий 1",
                List.of(getCorrectionProduct(1L),
                        getCorrectionProduct(2L),
                        getCorrectionProduct(3L))
        );
    }

    public static InventarizationProduct getInventarizationProduct(Long id) {
        return new InventarizationProduct(
                id,
                getProduct(id),
                BigDecimal.ONE,
                BigDecimal.ONE
        );
    }

    public static Inventarization getInventarization(Long id) {
        return new Inventarization(
                id,
                LocalDateTime.now(),
                getWarehouse(id),
                getCompany(id),
                false,
                "Комментарий 1",
                List.of(getInventarizationProduct(1L),
                        getInventarizationProduct(2L),
                        getInventarizationProduct(3L))
        );
    }

//    public static AcceptanceProduction getAcceptanceProduction(Long id) {
//        return AcceptanceProduction.builder()
//                .id(id)
//                .product(getProduct(1L))
//                .amount(100L)
//                .build();
//    }

//    public static Acceptance getAcceptance(Long id) {
//        return Acceptance.builder()
//                .id(id)
//                .acceptanceProduction(new ArrayList<>())
//                .contract(getContract(1L))
//                .contractor(getContractor(1L))
//                .comment("Комментарий " + id)
//                .incomingNumber("100")
//                .incomingNumberDate(LocalDate.now())
//                .warehouse(getWarehouse(1L))
//                .project(Project.builder()
//                        .id(1L)
//                        .name("name")
//                        .description("decr")
//                        .code("code")
//                        .build())
//                .build();
//    }

    public static AgentReports getAgentReports(Long id) {
        return AgentReports.builder()
                .id(id)
                .company(getCompany(1L))
                .contractor(getContractor(1L))
                .comitentSum(100L)
                .commentary("Коммент 1")
                .documentType(".doc")
                .number("1")
                .paid(10L)
                .printed(100L)
                .remunirationSum(100L)
                .sent(20L)
                .status("status 1")
                .time(LocalDateTime.now())
                .sum(1000L)
                .build();
    }

    public static SupplierAccount getSupplierAccount(Long id) {
        return SupplierAccount.builder()
                .id(id)
                .warehouse(getWarehouse(1L))
                .contract(getContract(1L))
                .contractor(getContractor(1L))
                .company(getCompany(1L))
                .date(LocalDateTime.now().toString())
                .comment("Комментарий")
                .isSpend(false)
                .build();
    }

    public static BalanceAdjustment getBalanceAdjustment(Long id) {
        return BalanceAdjustment.builder()
                .id(id)
                .date(LocalDateTime.now().toString())
                .company(getCompany(1L))
                .contractor(getContractor(1L))
                .account("Account 1")
                .cashOffice("Cash Office 1")
                .comment("Comment 1")
                .dateChanged(LocalDateTime.now().toString())
                .whoChanged("Who changed 1")
                .build();
    }
}

