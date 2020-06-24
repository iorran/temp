import { Moment } from 'moment';
import { IParcel } from 'app/shared/model/parcel.model';
import { IBuildDraw } from 'app/shared/model/build-draw.model';
import { IPlaceholderParcel } from 'app/shared/model/placeholder-parcel.model';
import { CustomsStatus } from 'app/shared/model/enumerations/customs-status.model';
import { NominationStatus } from 'app/shared/model/enumerations/nomination-status.model';
import { ModeOfTransport } from 'app/shared/model/enumerations/mode-of-transport.model';
import { MovementTransferType } from 'app/shared/model/enumerations/movement-transfer-type.model';

export interface ILegNomination {
  id?: number;
  customerId?: string;
  agent?: string;
  last3Cargoes?: string;
  productId?: string;
  ownVatNo?: string;
  vettingReference?: string;
  warehouseAddress?: string;
  warehouseNo?: string;
  warehouseTaxNo?: string;
  productReceiverVat?: string;
  customsStatus?: CustomsStatus;
  documentType?: string;
  clientClauseDetails?: string;
  status?: NominationStatus;
  counterPartyStatus?: NominationStatus;
  comment?: string;
  nominationTimestamp?: string;
  cnCode?: string;
  financialHold?: string;
  consignor?: string;
  blendingInstructions?: string;
  productAdditives?: string;
  homogenisation?: string;
  productReceiverId?: string;
  modeOfTransport?: ModeOfTransport;
  modeOfTransportEquipment?: string;
  dischargeDate?: string;
  loadportDate?: string;
  loadLocation?: string;
  dischargeLocation?: string;
  pleaseAdviseWarehouseTaxNo?: boolean;
  pleaseAdviseWarehouseNo?: boolean;
  pleaseAdviseWarehouseAddress?: boolean;
  pleaseAdviseProductReceiverVAT?: boolean;
  pleaseAdviseProductReceiver?: boolean;
  pleaseAdviseLoadport?: boolean;
  pleaseAdviseDisport?: boolean;
  preferentialOrigin?: string;
  productGrade?: string;
  productSpecification?: string;
  productAdditionalComments?: string;
  adn?: string;
  cdni?: string;
  customAdn?: string;
  customCdni?: string;
  movementTransferType?: MovementTransferType;
  customLoadLocation?: string;
  customDischargeLocation?: string;
  customReceiverName?: string;
  customReceiverAddress?: string;
  customConsignor?: string;
  notes?: string;
  terminalCompanyLegalEntity?: string;
  customTerminalCompanyLegalEntity?: string;
  iceDelivery?: boolean;
  nominationMetadataId?: number;
  parcels?: IParcel[];
  buildDraws?: IBuildDraw[];
  placeholderParcels?: IPlaceholderParcel[];
}

export const defaultValue: Readonly<ILegNomination> = {
  pleaseAdviseWarehouseTaxNo: false,
  pleaseAdviseWarehouseNo: false,
  pleaseAdviseWarehouseAddress: false,
  pleaseAdviseProductReceiverVAT: false,
  pleaseAdviseProductReceiver: false,
  pleaseAdviseLoadport: false,
  pleaseAdviseDisport: false,
  iceDelivery: false,
};
